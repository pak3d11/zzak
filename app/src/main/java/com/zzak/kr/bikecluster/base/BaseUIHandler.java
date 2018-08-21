package com.zzak.kr.bikecluster.base;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * MainThread 외에 SubThread 에서 UI 수정 및 컨트롤을 위한 서포트 클래스
 *
 * @author IT사업부 곽성규
 * @version 1.0.0
 * @date 2018-07-09 오후 1:01
 */
public class BaseUIHandler extends Handler {
    private WeakReference<BaseFragment> mFragment;
    private WeakReference<BaseActivity> mActivity;

    public BaseUIHandler(BaseFragment fragment) {
        this.mFragment = new WeakReference(fragment);
    }

    public BaseUIHandler(BaseActivity activity) {
        this.mActivity = new WeakReference(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        BaseFragment fragment = (BaseFragment)this.mFragment.get();
        if(fragment != null) {
            fragment.onHandleMessage(msg);
        }
    }
}