package com.umeng.soexample.activity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.umeng.soexample.R;
import com.umeng.soexample.fragment.MyFragment;
import com.umeng.soexample.fragment.SouYeFragment;
import com.umeng.soexample.mvp.view.DegateImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivityPresenter extends DegateImpl {
    private List<Fragment> mFragments=new ArrayList<>();
    String mtitle[]={"首页","我的"};
    private TabLayout main_tab;
    private ViewPager main_viewpager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void iniData() {
        super.iniData();
        mFragments.add(new SouYeFragment());
        mFragments.add(new MyFragment());

        main_viewpager = (ViewPager) get(R.id.main_viewpager);
        main_tab = (TabLayout) get(R.id.main_tab);

       main_viewpager.setAdapter(new FragmentPagerAdapter(((MainActivity)context).getSupportFragmentManager()) {
           @Override
           public Fragment getItem(int i) {
               return mFragments.get(i);
           }

           @Override
           public int getCount() {
               return mFragments.size();
           }
//
           @Nullable
           @Override
           public CharSequence getPageTitle(int position) {
               return mtitle[position];
           }
       });
        main_tab.setupWithViewPager(main_viewpager);
        main_tab.setTabMode(TabLayout.MODE_FIXED);
}

    private Context context;
    @Override
    public void setContext(Context context) {
        super.setContext(context);
        this.context=context;
    }
}
