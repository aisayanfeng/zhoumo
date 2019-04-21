package com.yanfeng.myapplication.presenter;

import com.yanfeng.myapplication.base.BasePresenter;
import com.yanfeng.myapplication.bean.DailyNewsBean;
import com.yanfeng.myapplication.bean.HotBean;
import com.yanfeng.myapplication.callback.ResultCallBack;
import com.yanfeng.myapplication.model.DailyNewsM;
import com.yanfeng.myapplication.model.HotM;
import com.yanfeng.myapplication.view.DailyNewsV;
import com.yanfeng.myapplication.view.HotV;

public class HotP extends BasePresenter<HotV> {
    private HotM hotM;

    @Override
    protected void initModel() {
        hotM = new HotM();
        mModels.add(hotM);
    }

    public void getData(){
        hotM.getData(new ResultCallBack<HotBean>() {
            @Override
            public void onSuccess(HotBean bean) {
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
