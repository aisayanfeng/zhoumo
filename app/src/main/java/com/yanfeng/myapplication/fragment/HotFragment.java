package com.yanfeng.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanfeng.myapplication.R;
import com.yanfeng.myapplication.adapter.RlvHotAdapter;
import com.yanfeng.myapplication.base.BaseFragment;
import com.yanfeng.myapplication.bean.HotBean;
import com.yanfeng.myapplication.presenter.EmptyP;
import com.yanfeng.myapplication.presenter.HotP;
import com.yanfeng.myapplication.view.EmptyV;
import com.yanfeng.myapplication.view.HotV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseFragment<HotV, HotP> implements HotV {

    private static String TAG="HotFragment";
    @BindView(R.id.xr)
    RecyclerView xr;
    Unbinder unbinder;
    private RlvHotAdapter adapter;

    @Override
    protected HotP initPresenter() {
        return new HotP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView() {
        xr.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<HotBean.RecentBean> list = new ArrayList<>();
        adapter = new RlvHotAdapter(getContext(), list);
        xr.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void setData(HotBean bean) {
        adapter.setData(bean);
    }
}
