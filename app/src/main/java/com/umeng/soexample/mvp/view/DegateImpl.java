package com.umeng.soexample.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public abstract class DegateImpl implements Degate {

    private View rootView;

    private SparseArray<View> views=new SparseArray<>();
    public <T extends View> T get(int id){
        T view = (T) views.get(id);
        if (view == null) {
            view = rootView.findViewById(id);
            views.put(id,view);
        }
        return view;
    }

    public void setOnclick(View.OnClickListener listener,int...ids){
        if (ids == null) {
            return;
        }
        for (int id:ids){
            get(id).setOnClickListener(listener);
        }
    }

    public void toast(String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void iniData() {

    }

    @Override
    public void create(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        rootView = layoutInflater.inflate(getLayoutId(), viewGroup, false);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    private Context context;
    @Override
    public void setContext(Context context) {
        this.context=context;
    }

    public abstract int getLayoutId() ;
    public void destoryView(){
        rootView=null;
    }

}

