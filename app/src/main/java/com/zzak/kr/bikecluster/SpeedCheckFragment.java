package com.zzak.kr.bikecluster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SpeedCheckFragment extends Fragment {

    public SpeedCheckFragment(){
        //onPause시 Crash나는것을 방지하기위한 빈 생성자
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        LinearLayout speedCheckLayout = (LinearLayout)inflater.inflate(R.layout.fragment_speed_check, container, false);
        return speedCheckLayout;
    }
}
