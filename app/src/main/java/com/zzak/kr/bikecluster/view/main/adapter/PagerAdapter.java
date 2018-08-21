package com.zzak.kr.bikecluster.view.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zzak.kr.bikecluster.view.home.fragment.HomeFragment;
import com.zzak.kr.bikecluster.view.map.MapFragment;
import com.zzak.kr.bikecluster.view.speedchek.SpeedCheckFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return new HomeFragment();

            case 1:
                return new SpeedCheckFragment();

            case 2:
                return new MapFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
