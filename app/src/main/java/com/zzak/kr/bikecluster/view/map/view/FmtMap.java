package com.zzak.kr.bikecluster.view.map.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.compa.gsk.base.BaseFragment;
import com.zzak.kr.bikecluster.R;
import com.zzak.kr.bikecluster.view.map.contract.ContractMap;
import com.zzak.kr.bikecluster.view.map.presenter.PresenterMap;

public class FmtMap extends BaseFragment<PresenterMap> implements ContractMap.View {

    private RecyclerView recyclerView;

    @Override
    protected boolean useMainFragment() {
        return false;
    }

    @Override
    protected int inflateLayout() {
        return R.layout.fmt_map;
    }

    @Override
    protected void viewFindById(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    @Override
    protected void viewSetting() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        this.mPresenter.setRecyclerView(recyclerView);
    }
}
