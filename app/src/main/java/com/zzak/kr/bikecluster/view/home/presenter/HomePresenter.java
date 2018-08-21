package com.zzak.kr.bikecluster.view.home.presenter;

import android.os.Bundle;

import com.zzak.kr.bikecluster.view.home.contract.HomeContract;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public void attachView(HomeContract.View view) {

    }

    @Override
    public void initView() {
        HomePresenter.this.mView.showToast("홈 프래그먼트의 프리센터 초기화 완료.");
        HomePresenter.this.mView.showText("바뀐 텍스트");
    }

    @Override
    public void detachView() {

    }
}
