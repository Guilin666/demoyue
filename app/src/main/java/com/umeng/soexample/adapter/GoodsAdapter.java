package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.model.GoodBean;
import com.umeng.soexample.view.ShopView;

import java.util.ArrayList;
import java.util.List;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.MyViewHolder> {

    private Context context;
    private List<GoodBean.DataBean.ListBean> list;


    public GoodsAdapter(Context context, List<GoodBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.good_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_data.setText(list.get(i).getSubhead());
        myViewHolder.tv_price.setText(list.get(i).getPrice()+"");
        String replace = list.get(i).getImages().replace("Https", "Http");
        Glide.with(context).load(replace.split("\\|")[0]).into(myViewHolder.item_img);
        myViewHolder.xuan.setChecked(list.get(i).isCheck());
        myViewHolder.xuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(i).isCheck()) {
                    list.get(i).setCheck(false);
                }else{
                    list.get(i).setCheck(true);
                }
                notifyItemChanged(i);
               // Toast.makeText(context, myViewHolder.xuan.isChecked()+"", Toast.LENGTH_SHORT).show();
                goosListener.setListChange();
            }
        });
        myViewHolder.shopView.setListData(list.get(i).getNum());

        myViewHolder.shopView.result(new ShopView.ShopListener() {
            @Override
            public void setShopList(int nums) {
                list.get(i).setNum(nums);
                notifyItemChanged(i);
                //点击调用
                goosListener.setListChange();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_data;
        ImageView item_img;
        CheckBox xuan;
        TextView tv_price;
        private  ShopView shopView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_data = (TextView) itemView.findViewById(R.id.tv_data);
            item_img = itemView.findViewById(R.id.item_img);
            xuan = (CheckBox) itemView.findViewById(R.id.xuan);
            tv_price = itemView.findViewById(R.id.tv_price);
            shopView = itemView.findViewById(R.id.sv_shop);
        }
    }

    private GoosListener goosListener;
    public void result(GoosListener goosListener){
        this.goosListener=goosListener;
    }
    public interface GoosListener{
        void setListChange();
    }
}
