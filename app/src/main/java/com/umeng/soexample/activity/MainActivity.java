package com.umeng.soexample.activity;

import com.umeng.soexample.mvp.presenter.BaseActivityPresenter;

public class MainActivity extends BaseActivityPresenter {


    @Override
    public Class getDegateClass() {
        return MainActivityPresenter.class;
    }

}
