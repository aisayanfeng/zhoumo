package com.yanfeng.myapplication.presenter;

import android.support.v4.app.Fragment;

import com.yanfeng.myapplication.base.BaseMvpView;
import com.yanfeng.myapplication.base.BasePresenter;
import com.yanfeng.myapplication.callback.MyCallBack;
import com.yanfeng.myapplication.model.v2exM;
import com.yanfeng.myapplication.view.V2exV;

import java.util.ArrayList;

public class V2exP extends BasePresenter<V2exV> {
    private v2exM model;

    @Override
    protected void initModel() {
        model = new v2exM();
        mModels.add(model);
    }

    public void getV2EX() {
        model.getV2ex(new MyCallBack() {
            @Override
            public void onMySuccess(ArrayList<String> titles, ArrayList<Fragment> fragments) {
                mView.setAllData(titles,fragments);
            }
        });
    }
}
