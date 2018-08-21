package com.compa.ikhp.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.compa.ikhp.R;
import com.compa.ikhp.utils.UtilAlert;
import com.compa.ikhp.utils.UtilDeviceInfo;
import com.compa.ikhp.utils.UtilViewIdGenerator;

/**
 * 매우 자주 사용되는 주 메소드를 정의 한 후,
 * 해당 class 를 상속 받아 빠르게 Fmt 를 만들기 위한 class
 *
 * @author IT사업부 곽성규
 * @version 1.0.0
 * @date 2018-07-09 오후 12:59
 */
public abstract class BaseActivity extends AppCompatActivity {
    public interface OnKeyBackPressedListener {
        void onBack();
    }
    public interface OnNewIntentListener {
        void onNewIntent(Bundle bundle);
    }
    public void setOnKeyBackPressedListener(OnKeyBackPressedListener listener) {
        if(listener != null){
            this.onKeyBackPressedListener = listener;
        }
    }
    public void setOnNewIntentListener(OnNewIntentListener listener) {
        this.onNewIntentListener = listener;
    }

    private Context context;
    private Toast mToast;
    private Snackbar mSnackBar;
    private ProgressDialog mProgressDialog;
    private int mainFrame;
    private String initFragmentName;
    private OnKeyBackPressedListener onKeyBackPressedListener;
    private OnNewIntentListener onNewIntentListener;

    public BaseActivity(){}

    protected abstract void initIntentData(Bundle bundle);
    protected abstract BaseFragment initFragment();
    protected abstract void findViewId();
    protected abstract void initData();

    public int getMainViewId() {
        return this.mainFrame;
    }

    public void baseActivityCallBack(Object... obj) {}

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(this.onNewIntentListener != null && intent != null) {
            this.onNewIntentListener.onNewIntent(intent.getExtras());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this.getApplication();
        this.initIntentData(savedInstanceState);
        if(this.initFragment() != null) {
            this.mainFrame = UtilViewIdGenerator.Companion.generateViewId();
            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            );
            layout.setLayoutParams(params);
            layout.setId(this.mainFrame);
            this.setContentView(layout);
            this.initFragmentName = this.initFragment().getClass().getName();
            this.replaceFragment(this.mainFrame, this.initFragment(), this.initFragmentName);
        }
        this.findViewId();
        this.initData();
    }

    public void replaceFragment(int id, BaseFragment fragment, String tag) {
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id, fragment, tag);
        ft.commit();
        fm.executePendingTransactions();
    }

    public BaseFragment getFindFragment(String tag) {
        BaseFragment fragment = (BaseFragment)this.getSupportFragmentManager().findFragmentByTag(tag);
        return fragment;
    }

    public BaseFragment getMainFragment(){
        return getFindFragment(initFragmentName);
    }

    public void showToast(String msg) {
        if(this.mToast == null) {
            this.mToast = Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_SHORT);
            this.mToast.show();
        } else {
            this.mToast.setText(msg);
            this.mToast.show();
        }
    }

    public void showSnackBar(String msg) {
        if(this.mSnackBar == null) {
            this.mSnackBar = Snackbar.make(this.getWindow().getDecorView().findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT);
            this.mSnackBar.show();
        } else {
            this.mSnackBar.setText(msg);
            this.mSnackBar.show();
        }
    }

    public void showSnackBar(String msg, String action, View.OnClickListener listener) {
        if(this.mSnackBar == null) {
            this.mSnackBar = Snackbar.make(this.getWindow().getDecorView().findViewById(android.R.id.content), msg, -1);
            this.mSnackBar.setAction(action, listener);
            this.mSnackBar.show();
        } else {
            this.mSnackBar.setText(msg);
            this.mSnackBar.setAction(action, listener);
            this.mSnackBar.show();
        }
    }

    public void showProgress(boolean cancelable, String message){
        if(this.isActivityAlive()){
            if(this.mProgressDialog != null && this.mProgressDialog.isShowing()){
                this.mProgressDialog.setCancelable(cancelable);
                this.mProgressDialog.setMessage(message);
                return;
            }
            if(this.mProgressDialog == null){
                this.mProgressDialog = new ProgressDialog(this);
            }
            this.mProgressDialog.setCancelable(cancelable);
            this.mProgressDialog.setMessage(message);
            if(this.mProgressDialog != null && !this.mProgressDialog.isShowing()){
                if(this.isActivityAlive()){
                    this.mProgressDialog.show();
                }
            }
        }
    }

    public void showAlert(String msg){
        if(this.isActivityAlive()){
            UtilAlert.AlertShow(this, false, "알림", msg, null, "확인", null,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        }
    }

    public void onApiErrorAlert(int code){
        if(!UtilDeviceInfo.isNetWorkEnable(getApplicationContext())){
            this.onNetworkOffAlert();
        }

        if(this.isActivityAlive()){
            UtilAlert.AlertShow(this, false, "알림", "에러코드 : " + code + "\n" + "에러가 발생 하였습니다." , null, "확인",null,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        }
    }

    public void onNetworkOffAlert(){
        if(this.isActivityAlive()){
            if(!UtilDeviceInfo.isNetWorkEnable(getApplicationContext())){
                UtilAlert.AlertShow(this, false, "알림", getString(R.string.network_error_message), "앱 종료", "확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        },
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
            }
        }
    }


    public void hideProgress(){
        if(this.mProgressDialog != null && this.mProgressDialog.isShowing()){
            if(isActivityAlive()){
                this.mProgressDialog.dismiss();
                this.mProgressDialog = null;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(this.onKeyBackPressedListener != null){
            onKeyBackPressedListener.onBack();
        }else {
            super.onBackPressed();
        }
    }

    public boolean isActivityAlive(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return !isFinishing() && !isDestroyed();
        } else {
            return !isFinishing();
        }
    }
}
