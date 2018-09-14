package com.zzak.kr.bikecluster.view.map.view;

import android.view.View;
import com.compa.gsk.base.BaseFragment;
import com.zzak.kr.bikecluster.R;
import com.zzak.kr.bikecluster.view.map.presenter.PresenterMap;

public class FmtMap extends BaseFragment<PresenterMap> {

    @Override
    protected boolean useMainFragment() {
        return false;
    }

    @Override
    protected int inflateLayout() {
        return R.layout.fmt_map;
    }

    @Override
    protected void viewFindById(View view) {

    }

    @Override
    protected void viewSetting() {

    }
}
