package com.zzak.kr.bikecluster.view.map.contract;

import android.support.v7.widget.RecyclerView;

/**
 * @author IT사업부 곽성규
 * @version 1.0.0
 * @date 2018-09-18 오전 10:15
 */
public interface ContractMap {

    interface View {

    }

    interface Presenter {
        void setRecyclerView(RecyclerView recyclerView);
    }

}
