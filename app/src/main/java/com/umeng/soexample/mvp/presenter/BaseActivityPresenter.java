package com.umeng.soexample.mvp.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.umeng.socialize.UMShareAPI;
import com.umeng.soexample.mvp.view.DegateImpl;

public abstract class BaseActivityPresenter<T extends DegateImpl> extends AppCompatActivity{

    private  T degate;

    public  abstract Class<T> getDegateClass();
    public BaseActivityPresenter(){
        try {
            degate = getDegateClass().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        degate.setContext(this);
        degate.create(getLayoutInflater(),null,savedInstanceState);
        setContentView(degate.getRootView());
        degate.iniData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        degate.destoryView();
        degate=null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}

