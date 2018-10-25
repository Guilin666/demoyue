package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.soexample.R;
import com.umeng.soexample.model.GoodBean;

import java.util.ArrayList;
import java.util.List;

public class GoodAdapter extends RecyclerView.Adapter<GoodAdapter.MyViewHolder> {
    private List<GoodBean.DataBean> data1=new ArrayList<>();
    private Context context;

    public GoodAdapter(Context context) {
        this.context=context;
    }
    public void setList( List<GoodBean.DataBean> data1){
        this.data1=data1;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.seller_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_seller.setText(data1.get(i).getSellerName());
        GoodsAdapter goodsAdapter = new GoodsAdapter(context, data1.get(i).getList());
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        myViewHolder.child_recycle.setLayoutManager(staggeredGridLayoutManager);
        myViewHolder.child_recycle.setAdapter(goodsAdapter);
        goodsAdapter.result(new GoodsAdapter.GoosListener() {
            @Override
            public void setListChange() {

                goodListener.setListDataChange(data1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_seller;
        RecyclerView child_recycle;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_seller=itemView.findViewById(R.id.tv_seller);
            child_recycle=itemView.findViewById(R.id.child_recycle);
        }
    }
    private GoodListener goodListener;
    public void result(GoodListener goodListener){
        this.goodListener=goodListener;
    }
    public interface GoodListener{
        void setListDataChange(List<GoodBean.DataBean> list);
    }
}
