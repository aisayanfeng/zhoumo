package com.yanfeng.myapplication.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanfeng.myapplication.R;
import com.yanfeng.myapplication.adapter.VpZhihuAdapter;
import com.yanfeng.myapplication.base.BaseFragment;
import com.yanfeng.myapplication.presenter.ZhihuDailyNewsP;
import com.yanfeng.myapplication.view.ZhihuDailyNewsV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhihuDailyNewsFragment extends BaseFragment<ZhihuDailyNewsV, ZhihuDailyNewsP> implements ZhihuDailyNewsV {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    Unbinder unbinder;
    private ArrayList<Integer> mTitles;
    private ArrayList<BaseFragment> mFragments;

    @Override
    protected ZhihuDailyNewsP initPresenter() {
        return new ZhihuDailyNewsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_daily_news;
    }

    @Override
    protected void initView() {
        initTitles();
        initFragments();

        VpZhihuAdapter adapter = new VpZhihuAdapter(getContext(), getChildFragmentManager(), mFragments, mTitles);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new DailyNewsFragment());
        mFragments.add(new HotFragment());
        mFragments.add(new SectionsFragment());
    }

    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(R.string.dailyNews);
        mTitles.add(R.string.hot);
        mTitles.add(R.string.sections);
    }
}
