package com.zzak.kr.bikecluster;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVIewPager;
    private TextView mapBtn;
    private TextView speedCheckBtn;
    private TextView homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVIewPager = findViewById(R.id.view_pager);
        mapBtn = findViewById(R.id.map);
        speedCheckBtn = findViewById(R.id.speedCheck);
        homeBtn = findViewById(R.id.home);

        mVIewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        mVIewPager.setCurrentItem(0); //처음 실행시 표시될 Fragment position 값

        //버튼클릭 이동시 해당 position Tag 설정
        homeBtn.setOnClickListener(movePageListener);
        homeBtn.setTag(0);

        speedCheckBtn.setOnClickListener(movePageListener);
        speedCheckBtn.setTag(1);

        mapBtn.setOnClickListener(movePageListener);
        mapBtn.setTag(2);

    }

    //버튼으로 페이지 이동시 리스너
    View.OnClickListener movePageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int position = (int)v.getTag();
            mVIewPager.setCurrentItem(position);
        }
    };
}
