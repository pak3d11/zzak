package com.zzak.kr.bikecluster.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.Toast;
import com.zzak.kr.bikecluster.R;
import com.zzak.kr.bikecluster.util.UtilAlert;
import com.zzak.kr.bikecluster.util.UtilDeviceInfo;

/**
 * 매우 자주 사용되는 주 메소드를 정의 한 후,
 * 해당 class 를 상속 받아 빠르게 Fmt 를 만들기 위한 BaseFragment class
 *
 * @author IT사업부 곽성규
 * @version 1.0.0
 * @date 2018-07-09 오후 1:00
 */

public abstract class BaseFragment extends Fragment {
    public interface IKeyboardChanged {
        void onKeyboardShown();
        void onKeyboardHidden();
    }
    public interface OnFragmentCallBackListener {
        void onFragmentCallBack(Object... obj);
    }
    public interface OnBackPressedListener{
        void onBack();
    }
    private Toast mToast;
    private Snackbar mSnackBar;
    private ProgressDialog progressDialog;
    public OnFragmentCallBackListener mCallBack;
    public OnBackPressedListener mOnBackPressedListener;
    protected BaseUIHandler mUiHandler;

    protected abstract void BundleData(Bundle savedInstanceState);
    protected abstract int inflateLayout();
    protected abstract void findView(View rootView);
    protected abstract void initData();

    public BaseFragment() {}

    public void setOnFragmentCallBackListener(OnFragmentCallBackListener listener) {
        this.mCallBack = listener;
    }

    protected void setFragmentCallBack(Object... results) {
        if(this.mCallBack != null) {
            this.mCallBack.onFragmentCallBack(results);
        }
    }

    public void setOnBackPressedListener(OnBackPressedListener listener){
        this.mOnBackPressedListener = listener;
    }

    public void setOnKeyboardListener(final EditText edit, final IKeyboardChanged listener) {
        edit.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if(BaseFragment.this.keyboardShown(edit.getRootView())) {
                    listener.onKeyboardShown();
                } else {
                    listener.onKeyboardHidden();
                }
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.mCallBack = (OnFragmentCallBackListener)this.getActivity();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        this.BundleData(savedInstanceState);
        this.mUiHandler = new BaseUIHandler(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(this.inflateLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.findView(view);
        this.initData();
    }

    private boolean keyboardShown(View rootView) {
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
        int heightDiff = rootView.getBottom() - r.bottom;
        return (float)heightDiff > 100.0F * dm.density;
    }

    public void refreshFragment(){
        this.initData();
    }

    public BaseFragment getMainFragment(){
        return ((BaseActivity)getActivity()).getMainFragment();
    }

    public BaseFragment getNowFragment(){
        BaseFragment fragment = (BaseFragment) getFragmentManager().findFragmentById(R.id.main_frame);
        return fragment;
    }

    public BaseFragment getFindChildFragment(String fragmentTag){
        BaseFragment fragment = (BaseFragment)this.getChildFragmentManager().findFragmentByTag(fragmentTag);
        return fragment;
    }

    public void showFragment(BaseFragment fragment, String tag){
        for (int i = 0; i < getFragmentManager().getBackStackEntryCount(); i++) {
            if (tag.equals(getFragmentManager().getBackStackEntryAt(i).getName())) {
                BaseFragment frag = ((BaseActivity) getActivity()).getFindFragment(getFragmentManager().getBackStackEntryAt(i).getName());
                getFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getFragmentManager().beginTransaction().remove(frag).commit();
                break;
            }
        }
        getFragmentManager().beginTransaction().add(R.id.main_frame, fragment, tag).addToBackStack(tag).commit();
        for (int i = 0; i < getFragmentManager().getBackStackEntryCount(); i++) {
            BaseFragment frag = ((BaseActivity) getActivity()).getFindFragment(getFragmentManager().getBackStackEntryAt(i).getName());
            if (tag.equals(getFragmentManager().getBackStackEntryAt(i).getName())) {
                getFragmentManager().beginTransaction().show(frag).commit();
            } else {
                getFragmentManager().beginTransaction().hide(frag).commit();
            }
        }
    }

    public void backStackFragment(){
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStackImmediate();
            BaseFragment frag = ((BaseActivity) getActivity())
                    .getFindFragment(getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount() - 1).getName());
            getFragmentManager().beginTransaction().show(frag).commit();
        }else {
            getActivity().setResult(Activity.RESULT_CANCELED);
            getActivity().finish();
        }
    }

    public void replaceChildFragment(int containerId, BaseFragment fragment, String fragmentTag){
        FragmentTransaction tr = getChildFragmentManager().beginTransaction();
        tr.replace(containerId, fragment, fragmentTag).commit();
    }

    public void showChildFragment(int containerId, BaseFragment fragment, String fragmentTag){
        for (int i = 0; i < this.getChildFragmentManager().getBackStackEntryCount(); i++) {
            if (fragmentTag.equals(this.getChildFragmentManager().getBackStackEntryAt(i).getName())) {
                BaseFragment frag = getFindChildFragment(this.getChildFragmentManager().getBackStackEntryAt(i).getName());
                this.getChildFragmentManager().popBackStack(fragmentTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                this.getChildFragmentManager().beginTransaction().remove(frag).commit();
                break;
            }
        }
        this.getChildFragmentManager().beginTransaction().add(containerId, fragment, fragmentTag).addToBackStack(fragmentTag).commit();
        for (int i = 0; i < this.getChildFragmentManager().getBackStackEntryCount(); i++) {
            BaseFragment frag = getFindChildFragment(this.getChildFragmentManager().getBackStackEntryAt(i).getName());
            if (fragmentTag.equals(this.getChildFragmentManager().getBackStackEntryAt(i).getName())) {
                this.getChildFragmentManager().beginTransaction().show(frag).commit();
            } else {
                this.getChildFragmentManager().beginTransaction().hide(frag).commit();
            }
        }
    }

    public void backStackChildFragment(){
        if(this.getChildFragmentManager().getBackStackEntryCount() > 1){
            this.getChildFragmentManager().popBackStackImmediate();
            BaseFragment frag = getFindChildFragment(this.getChildFragmentManager().getBackStackEntryAt(this.getChildFragmentManager().getBackStackEntryCount() - 1).getName());
            this.getChildFragmentManager().beginTransaction().show(frag).commit();
        }
    }

    public void replaceAnimTagFm(int id, Fragment fragment, String tag, int start_anim, int end_anim) {
        this.getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(start_anim, end_anim).replace(id, fragment, tag).commit();
    }

    public void replaceAnimFm(int id, Fragment fragment, int start_anim, int end_anim) {
        this.getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(start_anim, end_anim).replace(id, fragment).commit();
    }

    public void showToast(String msg) {
        if(this.mToast == null) {
            this.mToast = Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT);
            this.mToast.show();
        } else {
            this.mToast.setText(msg);
            this.mToast.show();
        }
    }

    public void showSnackBar(String msg) {
        if(this.mSnackBar == null) {
            this.mSnackBar = Snackbar.make(this.getActivity().getWindow().getDecorView().findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT);
            this.mSnackBar.show();
        } else {
            this.mSnackBar.setText(msg);
            this.mSnackBar.show();
        }
    }

    public void showSnackBar(String msg, String action, View.OnClickListener listener) {
        if(this.mSnackBar == null) {
            this.mSnackBar = Snackbar.make(this.getActivity().getWindow().getDecorView().findViewById(android.R.id.content), msg, -1);
            this.mSnackBar.setAction(action, listener);
            this.mSnackBar.show();
        } else {
            this.mSnackBar.setText(msg);
            this.mSnackBar.setAction(action, listener);
            this.mSnackBar.show();
        }
    }

    public void showProgress(boolean cancelable, String message){
        if(isFragmentAlive()){
            if(this.progressDialog != null && this.progressDialog.isShowing()){
                this.progressDialog.setCancelable(cancelable);
                this.progressDialog.setMessage(message);
                return;
            }
            if(this.progressDialog == null){
                this.progressDialog = new ProgressDialog(getContext());
            }
            this.progressDialog.setCancelable(cancelable);
            this.progressDialog.setMessage(message);
            if(this.progressDialog != null && !this.progressDialog.isShowing()){
                if(this.isFragmentAlive()){
                    this.progressDialog.show();
                }
            }
        }
    }

    public void hideProgress(){
        if(this.progressDialog != null && this.progressDialog.isShowing()){
            if(this.isFragmentAlive()){
                this.progressDialog.dismiss();
                this.progressDialog = null;
            }
        }
    }

    public void showAlert(String msg){
        if(this.isFragmentAlive()){
            UtilAlert.AlertShow(getContext(), false, "알림", msg, "앱 종료", "확인",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    },
            new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        }
    }

    public void onApiErrorAlert(int code){
        if(!UtilDeviceInfo.isNetWorkEnable(getContext())){
            this.onNetworkOffAlert();
        }

        if(this.isFragmentAlive()){
            UtilAlert.AlertShow(getContext(), false, "알림", "에러코드 : " + code + "\n" + "에러가 발생 하였습니다." , null, "확인",null,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        }
    }

    public void onNetworkOffAlert(){
        if(this.isFragmentAlive() && !UtilDeviceInfo.isNetWorkEnable(getContext())){
            UtilAlert.AlertShow(getContext(), false, "알림", getString(R.string.network_error_message), null, "확인",null,
                    new DialogInterface.OnClickListener() {

                     @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
            });

        }
    }

    public void onHandleMessage(Message msg) {
    }

    public void baseFragmentCallBack(Object... obj) {
    }

    public boolean isFragmentAlive(){
        return isAdded() && !isHidden() && !isDetached() && !isRemoving();
    }
}
