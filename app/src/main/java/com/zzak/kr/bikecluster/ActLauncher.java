package com.zzak.kr.bikecluster;

import com.compa.gsk.base.BaseActivity;
import com.compa.gsk.base.BaseFragment;
import com.zzak.kr.bikecluster.view.main.fragment.FmtMain;

public class ActLauncher extends BaseActivity implements BaseFragment.OnFragmentCallBackListener{


    @Override
    protected int inflateLayout() {
        return 0;
    }

    @Override
    protected BaseFragment mainFragment() {
        return FmtMain.newInstance(null);
    }

    @Override
    public void onFragmentCallBack(Object... objects) {

    }
}
