package com.yanfeng.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanfeng.myapplication.R;
import com.yanfeng.myapplication.adapter.RlvWeChatAdapter;
import com.yanfeng.myapplication.base.BaseFragment;
import com.yanfeng.myapplication.bean.WeChatBean;
import com.yanfeng.myapplication.presenter.WeChatP;
import com.yanfeng.myapplication.view.WeChatV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WechatFragment extends BaseFragment<WeChatV, WeChatP> implements WeChatV {


    @BindView(R.id.xr)
    RecyclerView xr;
    Unbinder unbinder;
    private RlvWeChatAdapter adapter;

    @Override
    protected WeChatP initPresenter() {
        return new WeChatP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initView() {
        xr.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<WeChatBean.NewslistBean> list = new ArrayList<>();
        adapter = new RlvWeChatAdapter(getContext(), list);
        xr.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }


    @Override
    public void setData(WeChatBean bean) {
        adapter.setData(bean);
    }
}
