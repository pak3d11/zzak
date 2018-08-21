package com.compa.ikhp.base;

import android.support.v4.app.FragmentManager;

/**
 * @author IT사업부 곽성규
 * @version 1.0.0
 * @date 2018-07-23 오후 6:36
 */
public interface BaseView {

    void onApiErrorAlert(int code);

    void onNetworkOffAlert();

    void showSnackBar(String msg);

    void showAlert(String msg);

    void showProgress(boolean cancelable, String message);

    void showToast(String msg);

    void hideProgress();

//    FragmentManager getFragmentManager()

//    void replaceFragment(int id, BaseFragment fragment, String tag);

//    FragmentActivity getActivity();

//    BaseFragment getMainFragment();

//    void setPresenter(T presenter);

//    void startActivityForResult(Intent intent, int requestCode);

//    void startActivity(Intent intent);

//    Context getContext();

}
