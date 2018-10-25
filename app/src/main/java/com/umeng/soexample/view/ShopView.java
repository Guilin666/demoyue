package com.umeng.soexample.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.umeng.soexample.R;
import com.umeng.soexample.adapter.GoodsAdapter;
import com.umeng.soexample.model.GoodBean;

import java.util.List;

public class ShopView extends RelativeLayout {

    private EditText text;
    private int num;

    public ShopView(Context context) {
        super(context);
        init(context);
    }

    public ShopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ShopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        View view = View.inflate(context,R.layout.shopview,null);
        text = view.findViewById(R.id.et_text);
        text.addTextChangedListener(new TextWatcher() {

            private String s;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                int trim = Integer.parseInt(editable.toString());
                if (shopListener != null) {
                        shopListener.setShopList(trim);
                    }


            }
        });

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        view.findViewById(R.id.bt_add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                text.setText(num+"");
                shopListener.setShopList(num);
            }
        });;
        view.findViewById(R.id.bt_jian).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(num>1){
                    num--;
                    text.setText(num+"");
                    shopListener.setShopList(num);
                }else{
                    Toast.makeText(context, "商品最少有个一件", Toast.LENGTH_SHORT).show();
                }

            }
        });
        addView(view);
    }

    public void setListData(int num) {
        this.num=num;
        text.setText(num+"");
    }

    private ShopListener shopListener;
    public void result(ShopListener shopListener){
        this.shopListener=shopListener;
    }


    public interface ShopListener{
        void setShopList(int num);
    }
}
