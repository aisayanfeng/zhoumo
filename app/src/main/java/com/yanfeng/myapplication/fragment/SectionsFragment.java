package com.yanfeng.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanfeng.myapplication.R;
import com.yanfeng.myapplication.adapter.RlvSectionAdapter;
import com.yanfeng.myapplication.base.BaseFragment;
import com.yanfeng.myapplication.bean.SectionBean;
import com.yanfeng.myapplication.presenter.SectionP;
import com.yanfeng.myapplication.view.SectionV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SectionsFragment extends BaseFragment<SectionV, SectionP> implements SectionV {


    @BindView(R.id.xr)
    RecyclerView xr;
    Unbinder unbinder;
    private RlvSectionAdapter adapter;
    private static String TAG="SectionFragment";

    @Override
    protected SectionP initPresenter() {
        return new SectionP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sections;
    }

    @Override
    protected void initView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        xr.setLayoutManager(gridLayoutManager);
        ArrayList<SectionBean.DataBean> list = new ArrayList<>();
        adapter = new RlvSectionAdapter(getContext(), list);
        xr.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void setData(SectionBean bean) {
        adapter.setData(bean);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }
}
