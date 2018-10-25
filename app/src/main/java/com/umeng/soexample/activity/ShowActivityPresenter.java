package com.umeng.soexample.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.umeng.soexample.R;
import com.umeng.soexample.model.GoodBean;
import com.umeng.soexample.mvp.view.DegateImpl;
import com.umeng.soexample.view.GoodView;

import java.util.ArrayList;
import java.util.List;

public class ShowActivityPresenter extends DegateImpl{
    private List<String> mDatas=new ArrayList<>();
    private GoodView goodView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    public void iniData() {
        super.iniData();
        final EditText sousou=get(R.id.sousou);
        goodView = get(R.id.goodview);
        sousou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = sousou.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    toast("不能为空");
                    return;
                }else{
                    mDatas.add(trim);
                }
                goodView.setList(mDatas);

            }
        });
    }
}
