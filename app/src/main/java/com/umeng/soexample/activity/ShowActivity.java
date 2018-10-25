package com.umeng.soexample.activity;

import com.umeng.soexample.mvp.presenter.BaseActivityPresenter;

public class ShowActivity extends BaseActivityPresenter {
    @Override
    public Class getDegateClass() {
        return ShowActivityPresenter.class;
    }
}
