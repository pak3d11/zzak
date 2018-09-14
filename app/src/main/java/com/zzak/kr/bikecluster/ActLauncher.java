package com.zzak.kr.bikecluster;

import android.os.Bundle;
import com.compa.gsk.base.BaseActivity;
import com.compa.gsk.base.BaseFragment;
import com.zzak.kr.bikecluster.view.main.fragment.FmtMain;

public class ActLauncher extends BaseActivity implements BaseFragment.OnFragmentCallBackListener{

    @Override
    protected void initIntentData(Bundle bundle) {

    }

    @Override
    protected BaseFragment mainFragment() {
        return FmtMain.newInstance(null);
    }

    @Override
    protected void viewFindById() {

    }

    @Override
    protected void viewSetting() {

    }

    @Override
    protected void viewInitData() {

    }

    @Override
    public void onFragmentCallBack(Object... objects) {

    }
}
