package com.yanfeng.myapplication.presenter;

import com.yanfeng.myapplication.base.BasePresenter;
import com.yanfeng.myapplication.bean.HotBean;
import com.yanfeng.myapplication.bean.SectionBean;
import com.yanfeng.myapplication.callback.ResultCallBack;
import com.yanfeng.myapplication.model.HotM;
import com.yanfeng.myapplication.model.SectionM;
import com.yanfeng.myapplication.view.HotV;
import com.yanfeng.myapplication.view.SectionV;

public class SectionP extends BasePresenter<SectionV> {
    private SectionM sectionM;

    @Override
    protected void initModel() {
        sectionM = new SectionM();
        mModels.add(sectionM);
    }

    public void getData(){
        sectionM.getData(new ResultCallBack<SectionBean>() {
            @Override
            public void onSuccess(SectionBean bean) {
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
