package com.zzak.kr.bikecluster.view.home.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.compa.gsk.base.BaseFragment;
import com.zzak.kr.bikecluster.R;
import com.zzak.kr.bikecluster.view.home.contract.ContractHome;
import com.zzak.kr.bikecluster.view.home.presenter.PresenterHome;

public class FmtHome extends BaseFragment<PresenterHome> implements ContractHome.View {

    private TextView operationRecord;
    private TextView totalDistance;
    private RecyclerView homeRecycler;

    @Override
    protected boolean useMainFragment() {
        return false;
    }

    @Override
    protected int inflateLayout() {

        return R.layout.fmt_home;
    }

    @Override
    protected void viewFindById(View view) {

        operationRecord = view.findViewById(R.id.operation_record);
        totalDistance = view.findViewById(R.id.total_distance);
        homeRecycler = view.findViewById(R.id.home_recycler);

    }

    @Override
    protected void viewSetting() {

    }

    @Override
    public void showText(String text) {

        //운행기록, 총이동거리 초기 text setting
        operationRecord.setText(getString(R.string.operation_record));
        totalDistance.setText(getString(R.string.total_distance));

    }
}
