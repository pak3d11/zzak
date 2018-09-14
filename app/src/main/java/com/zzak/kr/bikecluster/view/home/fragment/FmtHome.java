package com.zzak.kr.bikecluster.view.home.fragment;

import android.view.View;
import android.widget.TextView;
import com.compa.gsk.base.BaseFragment;
import com.zzak.kr.bikecluster.R;
import com.zzak.kr.bikecluster.view.home.contract.ContractHome;
import com.zzak.kr.bikecluster.view.home.presenter.PresenterHome;

public class FmtHome extends BaseFragment<PresenterHome> implements ContractHome.View{

    private TextView tv_test;

    @Override
    protected boolean useMainFragment() {
        return false;
    }

    @Override
    protected int inflateLayout() {
        return R.layout.fmt_home;
    }

    @Override
    protected void viewFindById(View view) {
        tv_test = view.findViewById(R.id.tv_test);
    }

    @Override
    protected void viewSetting() {

    }

    @Override
    public void showText(String text) {
        tv_test.setText(text);
    }
}
