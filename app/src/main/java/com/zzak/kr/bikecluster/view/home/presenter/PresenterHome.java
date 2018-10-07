package com.zzak.kr.bikecluster.view.home.presenter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.compa.gsk.base.BasePresenter;
import com.zzak.kr.bikecluster.view.home.Data.Data;
import com.zzak.kr.bikecluster.view.home.Data.FooterData;
import com.zzak.kr.bikecluster.view.home.Data.HeaderData;
import com.zzak.kr.bikecluster.view.home.Data.ListData;
import com.zzak.kr.bikecluster.view.home.adapter.AdapterHomeRv;
import com.zzak.kr.bikecluster.view.home.contract.ContractHome;
import com.zzak.kr.bikecluster.view.home.view.FmtHome;

public class PresenterHome extends BasePresenter<FmtHome> implements ContractHome.Presenter, AdapterHomeRv.RvAdapterListener {

    private AdapterHomeRv adapterHomeRv;

    @Override
    public void setRecyclerView(RecyclerView recyclerView) {

        adapterHomeRv = new AdapterHomeRv(this);
        recyclerView.setAdapter(adapterHomeRv);
    }

    @Override
    public void onViewCreated() {

        PresenterHome.this.mView.setText();
        adapterHomeRv.getItems().add(new Data(AdapterHomeRv.VIEW_HEADER, new HeaderData("날짜3", "시간3", "이동거리3")));

        for (int i = 0; i < 3; i++) {

            if (i == 2) {

                adapterHomeRv.getItems().add(new Data(AdapterHomeRv.VIEW_FOOTER, new FooterData("초기화")));

            } else {

                adapterHomeRv.getItems().add(new Data(AdapterHomeRv.VIEW_LIST, new ListData("날짜2", "시간2", "이동거리2")));

            }
        }
        adapterHomeRv.refreshAll(adapterHomeRv.getItems());
    }

    @Override
    public void onRvItemClick(int position) {

    }
}
