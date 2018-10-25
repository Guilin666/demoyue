package com.umeng.soexample.activity;

import com.umeng.soexample.mvp.presenter.BaseActivityPresenter;

public class GouActivity extends BaseActivityPresenter {


    @Override
    public Class getDegateClass() {
        return GouActivityPresenter.class;
    }
}
