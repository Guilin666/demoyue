package com.umeng.soexample.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.umeng.soexample.R;
import com.umeng.soexample.activity.GaoDeActivity;
import com.umeng.soexample.activity.GouActivity;
import com.umeng.soexample.activity.ShowActivity;
import com.umeng.soexample.model.BannerBean;
import com.umeng.soexample.model.GoodBean;
import com.umeng.soexample.mvp.view.DegateImpl;
import com.umeng.soexample.net.OkUtils;
import com.yzq.zxinglibrary.android.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class MyFragmentPresenter extends DegateImpl {

    private BGABanner bg_main;
    private List<String> mPics=new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void iniData() {
        super.iniData();
        bg_main = (BGABanner)get(R.id.bg_main);
        EditText search= get(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,ShowActivity.class));
            }
        });
        ImageView scan=(ImageView)get(R.id.scan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CaptureActivity.class));
            }
        });
        ImageView gou=(ImageView)get(R.id.gou);
        gou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, GouActivity.class));
        }
        });
        get(R.id.tu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, GaoDeActivity.class));
            }
        });


//        get(R.id.)
        doHttp();
        doBanner();

    }

    private void doBanner() {
        new OkUtils("http://www.zhaoapi.cn/ad/getAd").setOkLisener(new OkUtils.OkListener() {
            private String icon;

            @Override
            public void success(final String data) {
                BannerBean bannerBean = new Gson().fromJson(data, BannerBean.class);
                final List<BannerBean.DataBean> data1 = bannerBean.getData();
                for (int i=0;i<data1.size();i++){
                    String replace = data1.get(i).getIcon().replace("https", "http");
                    mPics.add(replace);
                }

                bg_main.setAdapter(new BGABanner.Adapter() {
                    @Override
                    public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {
                        Glide.with(context).load(data1.get(position).getIcon().replace("https","http")).into((ImageView) itemView)
                       ;
                    }
                });

                bg_main.setData(mPics,mPics);


            }
        });
    }

    private void doHttp() {
        new OkUtils("http://www.zhaoapi.cn/product/getCarts?uid=71").setOkLisener(new OkUtils.OkListener() {
            @Override
            public void success(String data) {
                GoodBean goodBean = new Gson().fromJson(data, GoodBean.class);

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
