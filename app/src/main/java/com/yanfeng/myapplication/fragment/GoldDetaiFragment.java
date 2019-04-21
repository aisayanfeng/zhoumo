package com.yanfeng.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanfeng.myapplication.R;
import com.yanfeng.myapplication.base.BaseFragment;
import com.yanfeng.myapplication.base.Constants;
import com.yanfeng.myapplication.presenter.EmptyP;
import com.yanfeng.myapplication.view.EmptyV;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldDetaiFragment extends BaseFragment<EmptyV, EmptyP> implements EmptyV {


    @BindView(R.id.tv)
    TextView tv;
    Unbinder unbinder;

    public static GoldDetaiFragment newInstance(String text){
        GoldDetaiFragment fragment = new GoldDetaiFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA,text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold_detai;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        String data = arguments.getString(Constants.DATA);
        tv.setText(data);
    }
}
