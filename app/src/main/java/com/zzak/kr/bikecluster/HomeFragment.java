package com.zzak.kr.bikecluster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class HomeFragment extends Fragment{

    public HomeFragment(){
        //onPause시 Crash나는것을 방지하기위한 빈 생성자
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        LinearLayout homeLayout = (LinearLayout)inflater.inflate(R.layout.fragment_home, container, false);
        return homeLayout;
    }
}
