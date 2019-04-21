package com.yanfeng.myapplication.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yanfeng.myapplication.R;
import com.yanfeng.myapplication.activity.NodeNaviActivity;
import com.yanfeng.myapplication.base.BaseFragment;
import com.yanfeng.myapplication.bean.V2exTabBean;
import com.yanfeng.myapplication.presenter.V2exP;
import com.yanfeng.myapplication.view.V2exV;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class V2exFragment extends BaseFragment<V2exV,V2exP> implements V2exV {
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.vp)
    ViewPager mVp;
    private static final String TAG = "V2EXFragment";

    public V2exFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold;
    }

    @OnClick({R.id.iv})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv:
                goToShow();
                break;
        }
    }

    @Override
    protected void initData() {
        mPresenter.getV2EX();
    }

    @Override
    protected V2exP initPresenter() {
        return new V2exP();
    }


    private void goToShow() {
        startActivity(new Intent(getContext(), NodeNaviActivity.class));
    }

    @Override
    public void setAllData( final ArrayList<String> titles, final ArrayList<Fragment> fragments) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                FragmentPagerAdapter adapter = new FragmentPagerAdapter(getChildFragmentManager()) {
                    @Override
                    public int getCount() {
                        return fragments.size();
                    }

                    @Override
                    public Fragment getItem(int i) {
                        return fragments.get(i);
                    }

                    @Nullable
                    @Override
                    public CharSequence getPageTitle(int position) {
                        return titles.get(position);
                    }
                };
                mVp.setAdapter(adapter);
                mTab.setupWithViewPager(mVp);
            }
        });

    }
}
