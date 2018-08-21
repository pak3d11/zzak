package com.zzak.kr.bikecluster.view.home.contract;

import com.zzak.kr.bikecluster.base.BasePresenter;
import com.zzak.kr.bikecluster.base.BaseView;

public interface HomeContract {

    interface View extends BaseView {
        void showText(String text);
    }

    interface Presenter extends BasePresenter<View> {

    }

}
