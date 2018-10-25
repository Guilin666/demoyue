package com.umeng.soexample.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.soexample.R;
import com.umeng.soexample.adapter.GoodAdapter;
import com.umeng.soexample.model.GoodBean;
import com.umeng.soexample.mvp.view.DegateImpl;
import com.umeng.soexample.net.OkUtils;

import java.util.List;

public class GouActivityPresenter extends DegateImpl {

    private RecyclerView recycle_main;
    private CheckBox quan;
    private List<GoodBean.DataBean> data1;
    private GoodAdapter goodAdapter;
    private TextView tv_num;
    private TextView tv_sum;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gou;
    }

    @Override
    public void iniData() {
        super.iniData();
        tv_num = get(R.id.tv_num);
        tv_sum = get(R.id.tv_sum);
        recycle_main = (RecyclerView)get(R.id.recycle_main);
        quan = get(R.id.quan);
        doHttp();

        quan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num=0;
                double sum=0;
                boolean checked = quan.isChecked();//获取当前全选按钮的状态
                for (int i=0;i<data1.size();i++){
                    List<GoodBean.DataBean.ListBean> list = data1.get(i).getList();
                    for (int j=0;j<list.size();j++){
                        list.get(j).setCheck(checked);
                        num+=list.get(j).getNum();
                        sum+=list.get(j).getPrice()*list.get(j).getNum();
                    }
                }

                if (checked==true) {

                    tv_num.setText("数量为:"+num);
                    tv_sum.setText("总价为:"+sum);

                }else {
                    tv_num.setText("数量为:0");
                    tv_sum.setText("总价为:0.0");

                }
                goodAdapter.notifyDataSetChanged();

            }
        });
    }

    private void doHttp() {
        new OkUtils("http://www.zhaoapi.cn/product/getCarts?uid=71").setOkLisener(new OkUtils.OkListener() {
            @Override
            public void success(String data) {
                GoodBean goodBean = new Gson().fromJson(data, GoodBean.class);
                data1 = goodBean.getData();
                goodAdapter = new GoodAdapter(context);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recycle_main.setLayoutManager(linearLayoutManager);
                recycle_main.setAdapter(goodAdapter);
                goodAdapter.setList(data1);
                goodAdapter.result(new GoodAdapter.GoodListener() {
                    @Override
                    public void setListDataChange(List<GoodBean.DataBean> list) {
                        double price=0;
                        int num=0;
                        int num1=0;
                        int num2=0;
                        for (int i =0;i<list.size();i++){
                            List<GoodBean.DataBean.ListBean> list1 = list.get(i).getList();

                            for(int j =0;j<list1.size();j++){
                                num1++;
                                if(list1.get(j).isCheck()){
                                    price+=list1.get(j).getPrice()*list1.get(j).getNum();
                                    num+=list1.get(j).getNum();
                                    num2++;
                                }

                            }
                        }
                        if(num2<num1){
                            quan.setChecked(false);
                        }else{
                            quan.setChecked(true);
                        }
                        tv_num.setText("数量为:"+num);
                        tv_sum.setText("总价为:"+price);
                        goodAdapter.setList(list);
                    }
                });

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
