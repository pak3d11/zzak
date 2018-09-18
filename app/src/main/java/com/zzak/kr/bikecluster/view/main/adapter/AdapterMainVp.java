package com.zzak.kr.bikecluster.view.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zzak.kr.bikecluster.view.home.fragment.FmtHome;
import com.zzak.kr.bikecluster.view.map.view.FmtMap;
import com.zzak.kr.bikecluster.view.speedchek.view.FmtSpeedCheck;

public class AdapterMainVp extends FragmentStatePagerAdapter {

    private int tabCount;

    public AdapterMainVp(FragmentManager fm, int tabCount) {
        super(fm);

        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                return new FmtHome();

            case 1:
                return new FmtSpeedCheck();

            case 2:
                return new FmtMap();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
