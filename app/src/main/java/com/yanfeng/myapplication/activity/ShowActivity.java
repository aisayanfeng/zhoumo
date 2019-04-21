package com.yanfeng.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.yanfeng.myapplication.R;
import com.yanfeng.myapplication.adapter.RlvShowAdapter;
import com.yanfeng.myapplication.base.BaseActivity;
import com.yanfeng.myapplication.base.Constants;
import com.yanfeng.myapplication.bean.GoldShowBean;
import com.yanfeng.myapplication.presenter.EmptyP;
import com.yanfeng.myapplication.view.EmptyV;
import com.yanfeng.myapplication.widget.SimleToouchHelperCallBack;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends BaseActivity<EmptyV, EmptyP> implements EmptyV {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.xr)
    RecyclerView xr;
    private ArrayList<GoldShowBean> mList;

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected void initView() {
        mList = (ArrayList<GoldShowBean>)getIntent().getSerializableExtra(Constants.DATA);
        toolbar.setTitle(R.string.speial_show);
        toolbar.setNavigationIcon(R.mipmap.ic_close);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAct();
            }
        });

        xr.setLayoutManager(new LinearLayoutManager(this));
        RlvShowAdapter adapter = new RlvShowAdapter(mList);
        xr.setAdapter(adapter);
        xr.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        SimleToouchHelperCallBack simleToouchHelperCallBack=new SimleToouchHelperCallBack(adapter);
        simleToouchHelperCallBack.setmSwipeEnable(false);
        ItemTouchHelper helper = new ItemTouchHelper(simleToouchHelperCallBack);
        helper.attachToRecyclerView(xr);
    }

    private void finishAct() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA,mList);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finishAct();
    }
}
