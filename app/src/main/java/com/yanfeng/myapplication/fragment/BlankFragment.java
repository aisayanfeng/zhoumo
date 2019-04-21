package com.yanfeng.myapplication.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanfeng.myapplication.R;
import com.yanfeng.myapplication.activity.ShowActivity;
import com.yanfeng.myapplication.activity.VShowActivity;
import com.yanfeng.myapplication.adapter.V2exAdapter;
import com.yanfeng.myapplication.base.BaseFragment;
import com.yanfeng.myapplication.bean.DocumentBean;
import com.yanfeng.myapplication.net.V2exService;
import com.yanfeng.myapplication.presenter.BlankPresenter;
import com.yanfeng.myapplication.view.BlankView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends BaseFragment<BlankView,BlankPresenter> implements BlankView {


    @BindView(R.id.xr)
    RecyclerView mRlv;
    private V2exAdapter adapter;
    private String href;


    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstant(String href) {
        BlankFragment blankFragment = new BlankFragment();
        Bundle bundle=new Bundle();
        bundle.putString("href",href);
        blankFragment.setArguments(bundle);
        return blankFragment;
    }

    @Override
    protected BlankPresenter initPresenter() {
        return new BlankPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void initView() {
        ArrayList<DocumentBean> list = new ArrayList<>();
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new V2exAdapter(list, getContext());
        mRlv.setAdapter(adapter);
        Bundle bundle = getArguments();
        href = bundle.getString("href");
        adapter.setOnItemClickListener(new V2exAdapter.OnItemClickListener() {
            @Override
            public void itemClick(DocumentBean documentBean) {
                Intent intent = new Intent(getContext(), VShowActivity.class);
                intent.putExtra("url", V2exService.mUrl+documentBean.getUrl());
                intent.putExtra("title",documentBean.getTitle());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getBlank(href);
    }

    @Override
    public void updateDoc(final ArrayList<DocumentBean> bean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setAll(bean);
            }
        });
    }
}
