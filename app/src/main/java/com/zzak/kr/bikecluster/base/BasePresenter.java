package com.compa.ikhp.base;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.Spinner;

import java.lang.ref.WeakReference;

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
