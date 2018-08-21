package com.zzak.kr.bikecluster.view.main.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.zzak.kr.bikecluster.R;
import com.zzak.kr.bikecluster.base.BaseActivity;
import com.zzak.kr.bikecluster.base.BaseFragment;
import com.zzak.kr.bikecluster.view.main.adapter.PagerAdapter;
import com.zzak.kr.bikecluster.view.main.fragment.MainFragment;

public class MainActivity extends BaseActivity implements BaseFragment.OnFragmentCallBackListener{

    @Override
    protected void initIntentData(Bundle bundle) {

    }

    @Override
    protected BaseFragment initFragment() {
        return MainFragment.newInstance(null);
    }

    @Override
    protected void findViewId() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onFragmentCallBack(Object... obj) {

    }
}
