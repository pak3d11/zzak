package com.zzak.kr.bikecluster.base;


import android.os.Bundle;

/**
 * @author IT사업부 곽성규
 * @version 1.0.0
 * @date 2018-07-23 오후 5:44
 */
public interface BasePresenter<T> {

    void initData(Bundle bundle);

    void attachView(T view);

    void initView();

    void detachView();

//    void setViewPager(ViewPager viewPager);
//    void setRecyclerView(RecyclerView recyclerView);
//    void setSpinner(Spinner spinner);
//    void onActivityResult(int requestCode, int resultCode, Intent data);
//    void onHiddenChanged(boolean state);
}
