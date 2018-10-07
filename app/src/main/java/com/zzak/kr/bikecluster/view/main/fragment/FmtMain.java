package com.zzak.kr.bikecluster.view.main.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.compa.gsk.base.BaseFragment;
import com.zzak.kr.bikecluster.R;
import com.zzak.kr.bikecluster.view.main.adapter.AdapterMainVp;
import com.zzak.kr.bikecluster.view.main.presenter.PresenterMain;

public class FmtMain extends BaseFragment<PresenterMain> {

    public static BaseFragment newInstance(Bundle bundle) {
        BaseFragment fragment = new FmtMain();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    private ViewPager mViewPager;
    private TabLayout tabLayout;

    @Override
    protected boolean useMainFragment() {
        return false;
    }

    @Override
    protected int inflateLayout() {
        return R.layout.fmt_main;
    }

    @Override
    protected void viewFindById(View view) {

        mViewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tabLayout);
    }

    @Override
    protected void viewSetting() {

        //탭 추가
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.home)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.speed_check)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.map)));

        mViewPager.setAdapter(new AdapterMainVp(getFragmentManager(), tabLayout.getTabCount()));
        mViewPager.setCurrentItem(0); //처음 실행시 표시될 Fragment position 값

        //탭에따른 화면 이동 setting
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
