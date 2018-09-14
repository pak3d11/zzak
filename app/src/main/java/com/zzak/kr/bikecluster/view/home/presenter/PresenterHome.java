package com.zzak.kr.bikecluster.view.home.presenter;

import com.compa.gsk.base.BasePresenter;
import com.zzak.kr.bikecluster.view.home.contract.ContractHome;
import com.zzak.kr.bikecluster.view.home.fragment.FmtHome;

public class PresenterHome extends BasePresenter<FmtHome> implements ContractHome.Presenter {

    @Override
    public void onViewCreated() {
        PresenterHome.this.mView.showToast("홈 프래그먼트의 프리센터 초기화 완료.");
        PresenterHome.this.mView.showText("바뀐 텍스트");
    }
}
