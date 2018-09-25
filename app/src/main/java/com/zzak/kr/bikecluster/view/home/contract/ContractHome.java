package com.zzak.kr.bikecluster.view.home.contract;

import android.support.v7.widget.RecyclerView;

public interface ContractHome {

    interface View {
        void setText();
    }

    interface Presenter {
        void setRecyclerView(RecyclerView recyclerView);

    }

}
