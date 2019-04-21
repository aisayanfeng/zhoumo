package com.yanfeng.myapplication.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yanfeng.myapplication.R;
import com.yanfeng.myapplication.activity.FormerActivity;
import com.yanfeng.myapplication.adapter.RlvDailyNewsAdapter;
import com.yanfeng.myapplication.base.BaseFragment;
import com.yanfeng.myapplication.bean.BeforeBean;
import com.yanfeng.myapplication.bean.DailyNewsBean;
import com.yanfeng.myapplication.presenter.BeforePresenter;
import com.yanfeng.myapplication.presenter.DailyNewsP;
import com.yanfeng.myapplication.view.BeforeBeanView;
import com.yanfeng.myapplication.view.DailyNewsV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyNewsFragment extends BaseFragment<DailyNewsV, DailyNewsP> implements DailyNewsV,BeforeBeanView {

    private static final String TAG = "DailyNewsFragment";
    @BindView(R.id.xr)
    RecyclerView xr;
    Unbinder unbinder;
    @BindView(R.id.fab_calender)
    FloatingActionButton fabCalender;
    Unbinder unbinder1;
    private boolean bo = true;
    private RlvDailyNewsAdapter adapter;
    private String data1;

    @Override
    protected DailyNewsP initPresenter() {
        return new DailyNewsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily_news;
    }

    @Override
    protected void initView() {
        xr.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<DailyNewsBean.StoriesBean> newList = new ArrayList<>();
        ArrayList<DailyNewsBean.TopStoriesBean> banners = new ArrayList<>();
        adapter = new RlvDailyNewsAdapter(getContext(), newList, banners);
        xr.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        if (bo) {
            mPresenter.getData();
        } else {
            initData2(data1);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==2){
            data1 = data.getStringExtra("data");
            Toast.makeText(getContext(), data1, Toast.LENGTH_SHORT).show();
            if(data1.equals(data)){
                initData();
                bo=true;
            }else {
                initData2(data1);
                bo=false;
            }
        }
    }

    private void initData2(String data) {
        BeforePresenter beforePresenter = new BeforePresenter(this);
        beforePresenter.getDatas(data);
    }

    @Override
    public void setData(DailyNewsBean bean) {
        adapter.setData(bean);
    }

    @OnClick(R.id.fab_calender)
    public void click() {
        Toast.makeText(getContext(), "111", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), FormerActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    public void onSuccesss(BeforeBean beforeBean) {

    }
}
