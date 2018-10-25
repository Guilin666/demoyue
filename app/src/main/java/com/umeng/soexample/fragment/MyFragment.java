package com.umeng.soexample.fragment;

import com.umeng.soexample.mvp.presenter.BaseFragmentPresenter;

public class MyFragment extends BaseFragmentPresenter {
    @Override
    public Class getDegateClass() {
        return MyFragmentPresenter.class;
    }
}
