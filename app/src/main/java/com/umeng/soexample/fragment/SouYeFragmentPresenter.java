package com.umeng.soexample.fragment;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.abner.ming.MingUtils;
import com.abner.ming.ResultListener;
import com.abner.ming.UmengBean;
import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.mvp.view.DegateImpl;
import com.umeng.soexample.view.WaterView;

public class SouYeFragmentPresenter extends DegateImpl {


    private RelativeLayout.LayoutParams layoutParams;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_souye;
    }

    @Override
    public void iniData() {
        super.iniData();
         final ImageView logo=(ImageView)get(R.id.logoo);
        ImageView share=(ImageView)get(R.id.share);
         logo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 MingUtils.login((AppCompatActivity) context, 0, new ResultListener() {
                     @Override
                     public void success(UmengBean umengBean) {
                         Glide.with(context).load(umengBean.getIconurl()).into(logo);
//
                         Toast.makeText(context, ""+umengBean.getIconurl(), Toast.LENGTH_SHORT).show();
                     }
                 });
             }
         });

         share.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                MingUtils.shared((AppCompatActivity) context,0,"","","","");
             }
         });

        WaterView waterView=(WaterView)get(R.id.waterview);
        layoutParams = (RelativeLayout.LayoutParams) logo.getLayoutParams();
        waterView.getAnn(new WaterView.AnnListener() {
            @Override
            public void getY(float y) {
                layoutParams.setMargins(0,0,0, (int) y);
                logo.setLayoutParams(layoutParams);
            }
        });

    }

    private Context context;
    @Override
    public void setContext(Context context) {
        super.setContext(context);
        this.context=context;
    }
}
