package com.umeng.soexample.fragment;

import com.umeng.soexample.mvp.presenter.BaseFragmentPresenter;

public class SouYeFragment extends BaseFragmentPresenter{

    @Override
    public Class getDegateClass() {
        return SouYeFragmentPresenter.class;
    }
}
