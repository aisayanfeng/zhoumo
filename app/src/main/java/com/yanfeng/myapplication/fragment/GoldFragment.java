package com.yanfeng.myapplication.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yanfeng.myapplication.R;
import com.yanfeng.myapplication.activity.ShowActivity;
import com.yanfeng.myapplication.adapter.VPGoldAdapter;
import com.yanfeng.myapplication.base.BaseFragment;
import com.yanfeng.myapplication.base.Constants;
import com.yanfeng.myapplication.bean.GoldShowBean;
import com.yanfeng.myapplication.presenter.GoldP;
import com.yanfeng.myapplication.view.GoldV;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldFragment extends BaseFragment<GoldV, GoldP> {


    Unbinder unbinder;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.vp)
    ViewPager vp;
    Unbinder unbinder1;
    private ArrayList<BaseFragment> mFragments;
    private ArrayList<GoldShowBean> mList;

    @Override
    protected GoldP initPresenter() {
        return new GoldP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold;
    }

    @Override
    protected void initView() {
        initTitle();
        setFragments();
    }

    private void setFragments() {
        initFragments();
        VPGoldAdapter adapter = new VPGoldAdapter(getChildFragmentManager(), mFragments, mList);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            GoldShowBean bean = mList.get(i);
            if (bean.isChcked) {
                mFragments.add(GoldDetaiFragment.newInstance(bean.title));
            }
        }
    }

    private void initTitle() {
        mList = new ArrayList<>();
        mList.add(new GoldShowBean("工具资源", true));
        mList.add(new GoldShowBean("Android", true));
        mList.add(new GoldShowBean("iOs", true));
        mList.add(new GoldShowBean("设计", true));
        mList.add(new GoldShowBean("产品", true));
        mList.add(new GoldShowBean("阅读", true));
        mList.add(new GoldShowBean("前端", true));
        mList.add(new GoldShowBean("后端", true));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    @OnClick(R.id.iv)
    public void click(View v) {
        switch (v.getId()){
            case R.id.iv:
                go2ShowActivity();
                break;
        }
    }

    private void go2ShowActivity() {
        Intent intent = new Intent(getContext(), ShowActivity.class);
        intent.putExtra(Constants.DATA,mList);
        startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data!=null){
            if(requestCode==100 && resultCode== Activity.RESULT_OK){
                mList= (ArrayList<GoldShowBean>) data.getSerializableExtra(Constants.DATA);
                setFragments();
            }
        }
    }
}
