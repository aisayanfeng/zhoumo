package com.yanfeng.myapplication.presenter;

import com.yanfeng.myapplication.base.BasePresenter;
import com.yanfeng.myapplication.bean.HotBean;
import com.yanfeng.myapplication.bean.WeChatBean;
import com.yanfeng.myapplication.callback.ResultCallBack;
import com.yanfeng.myapplication.model.WeChatM;
import com.yanfeng.myapplication.view.WeChatV;

public class WeChatP extends BasePresenter<WeChatV> {

    private WeChatM weChatM;

    @Override
    protected void initModel() {
        weChatM = new WeChatM();
        mModels.add(weChatM);
    }

    public void getData(){
        weChatM.getData(new ResultCallBack<WeChatBean>() {
            @Override
            public void onSuccess(WeChatBean bean) {
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
