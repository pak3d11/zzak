package com.zzak.kr.bikecluster.view.main.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import com.zzak.kr.bikecluster.R;
import com.zzak.kr.bikecluster.base.BaseFragment;
import com.zzak.kr.bikecluster.view.main.adapter.PagerAdapter;

public class MainFragment extends BaseFragment implements View.OnClickListener{

    public static final String FRAGMENT_TAG = MainFragment.class.getName();
    public static BaseFragment newInstance(Bundle bundle){
        BaseFragment fragment = new MainFragment();
        if(bundle != null){
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    private ViewPager mViewPager;
    private TextView mapBtn;
    private TextView speedCheckBtn;
    private TextView homeBtn;

    @Override
    protected void BundleData(Bundle savedInstanceState) {

    }

    @Override
    protected int inflateLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void findView(View rootView) {
        mViewPager = rootView.findViewById(R.id.view_pager);
        mapBtn = rootView.findViewById(R.id.map);
        speedCheckBtn = rootView.findViewById(R.id.speedCheck);
        homeBtn = rootView.findViewById(R.id.home);
    }

    @Override
    protected void initData() {
        mViewPager.setAdapter(new PagerAdapter(getFragmentManager()));
        mViewPager.setCurrentItem(0); //처음 실행시 표시될 Fragment position 값

        //버튼클릭 이동시 해당 position Tag 설정
        homeBtn.setTag(0);
        speedCheckBtn.setTag(1);
        mapBtn.setTag(2);

        speedCheckBtn.setOnClickListener(this);
        homeBtn.setOnClickListener(this);
        mapBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int tag = (int)view.getTag();
        mViewPager.setCurrentItem(tag);

        switch (view.getId()){
            case R.id.map :
                break;

            case R.id.speedCheck :
                break;

            case R.id.home :
                break;
        }
    }
}
