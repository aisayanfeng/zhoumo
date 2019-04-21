package com.yanfeng.myapplication.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.yanfeng.myapplication.R;
import com.yanfeng.myapplication.adapter.NodeAd;
import com.yanfeng.myapplication.base.BaseActivity;
import com.yanfeng.myapplication.bean.V2exBean;
import com.yanfeng.myapplication.presenter.NodePresenter;
import com.yanfeng.myapplication.view.NodeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import qdx.stickyheaderdecoration.NormalDecoration;


public class NodeNaviActivity extends BaseActivity<NodeView, NodePresenter> implements NodeView {

    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private NodeAd nodeAd;


    @Override
    protected NodePresenter initPresenter() {
        return new NodePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_node_navi;
    }

    @Override
    protected void initView() {
        mToolbar.setTitle("节点导航");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<V2exBean.DataBean> list = new ArrayList<>();
        nodeAd = new NodeAd(this, list);
        mRlv.setAdapter(nodeAd);
    }

    @Override
    protected void initData() {
        mPresenter.getNode();
    }


    @Override
    public void updateNode(final List<V2exBean.DataBean> data) {
        NormalDecoration normalDecoration = new NormalDecoration() {
            @Override
            public String getHeaderName(int i) {
                return data.get(i).getName();
            }
        };
        mRlv.addItemDecoration(normalDecoration);
        nodeAd.setAll(data);
    }
}
