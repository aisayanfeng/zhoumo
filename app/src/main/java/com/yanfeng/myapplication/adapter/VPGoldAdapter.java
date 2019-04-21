package com.yanfeng.myapplication.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yanfeng.myapplication.base.BaseFragment;
import com.yanfeng.myapplication.bean.GoldShowBean;

import java.util.ArrayList;

public class VPGoldAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> mFragments;
    private ArrayList<GoldShowBean> mTitles;
    private ArrayList<String> mNewTitles=new ArrayList<>();
    public VPGoldAdapter(FragmentManager fm,
                         ArrayList<BaseFragment> fragments,
                         ArrayList<GoldShowBean> titles) {
        super(fm);
        mFragments=fragments;
        mTitles=titles;
        for (int i = 0; i < mTitles.size(); i++) {
            GoldShowBean bean = mTitles.get(i);
            if(bean.isChcked){
                mNewTitles.add(bean.title);
            }
        }
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mNewTitles.get(position);
    }
}
