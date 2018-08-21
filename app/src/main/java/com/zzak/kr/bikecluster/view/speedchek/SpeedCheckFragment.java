package com.zzak.kr.bikecluster.view.speedchek;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zzak.kr.bikecluster.R;
import com.zzak.kr.bikecluster.base.BaseFragment;
import com.zzak.kr.bikecluster.view.map.MapFragment;

public class SpeedCheckFragment extends BaseFragment {

    public static final String FRAGMENT_TAG = SpeedCheckFragment.class.getName();
    public static BaseFragment newInstance(Bundle bundle){
        BaseFragment fragment = new SpeedCheckFragment();
        if(bundle != null){
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected void BundleData(Bundle savedInstanceState) {

    }

    @Override
    protected int inflateLayout() {
        return R.layout.fragment_speed_check;
    }

    @Override
    protected void findView(View rootView) {

    }

    @Override
    protected void initData() {

    }
}
