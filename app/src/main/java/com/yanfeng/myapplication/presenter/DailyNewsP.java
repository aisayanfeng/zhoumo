package com.yanfeng.myapplication.presenter;

import com.yanfeng.myapplication.base.BasePresenter;
import com.yanfeng.myapplication.bean.DailyNewsBean;
import com.yanfeng.myapplication.callback.ResultCallBack;
import com.yanfeng.myapplication.model.DailyNewsM;
import com.yanfeng.myapplication.view.DailyNewsV;
import com.yanfeng.myapplication.view.ZhihuDailyNewsV;

public class DailyNewsP  extends BasePresenter<DailyNewsV> {

    private DailyNewsM mDailyNewsM;

    @Override
    protected void initModel() {
        mDailyNewsM = new DailyNewsM();
        mModels.add(mDailyNewsM);
    }

    public void getData(){
        mDailyNewsM.getData(new ResultCallBack<DailyNewsBean>() {
            @Override
            public void onSuccess(DailyNewsBean bean) {
                if(bean!=null){
                    if(mView!=null){
                        mView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
