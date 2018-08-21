package com.zzak.kr.bikecluster.view.home.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzak.kr.bikecluster.R;
import com.zzak.kr.bikecluster.base.BaseFragment;
import com.zzak.kr.bikecluster.view.home.contract.HomeContract;
import com.zzak.kr.bikecluster.view.home.presenter.HomePresenter;

public class HomeFragment extends BaseFragment implements HomeContract.View{

    private HomeContract.Presenter mPresenter;
    private TextView tv_test;

    @Override
    protected void BundleData(Bundle savedInstanceState) {
        mPresenter = new HomePresenter();
        mPresenter.initData(getArguments());
    }

    @Override
    protected int inflateLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void findView(View rootView) {
        tv_test = rootView.findViewById(R.id.tv_test);
    }

    @Override
    protected void initData() {
        mPresenter.attachView(this);
        mPresenter.initView();
    }

    @Override
    public void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showText(String text){
        tv_test.setText(text);
    }

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }
}
