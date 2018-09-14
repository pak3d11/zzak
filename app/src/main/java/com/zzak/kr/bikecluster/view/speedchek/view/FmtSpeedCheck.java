package com.zzak.kr.bikecluster.view.speedchek.view;

import android.os.Bundle;
import android.view.View;
import com.compa.gsk.base.BaseFragment;
import com.zzak.kr.bikecluster.R;
import com.zzak.kr.bikecluster.view.speedchek.presenter.PresenterSpeedCheck;

public class FmtSpeedCheck extends BaseFragment<PresenterSpeedCheck> {

    public static final String FRAGMENT_TAG = FmtSpeedCheck.class.getName();
    public static BaseFragment newInstance(Bundle bundle){
        BaseFragment fragment = new FmtSpeedCheck();
        if(bundle != null){
            fragment.setArguments(bundle);
        }
        return fragment;
    }


    @Override
    protected boolean useMainFragment() {
        return false;
    }

    @Override
    protected int inflateLayout() {
        return R.layout.fmt_speed_check;
    }

    @Override
    protected void viewFindById(View view) {

    }

    @Override
    protected void viewSetting() {

    }
}
