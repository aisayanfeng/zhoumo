package com.yanfeng.myapplication.presenter;


import com.yanfeng.myapplication.base.BasePresenter;
import com.yanfeng.myapplication.bean.BeforeBean;
import com.yanfeng.myapplication.model.BeforeModel;
import com.yanfeng.myapplication.net.BeforeCallBack;
import com.yanfeng.myapplication.view.BeforeBeanView;

public class BeforePresenter extends BasePresenter<BeforeBeanView> {

    private BeforeModel beforeModel;
    private BeforeBeanView beforeBeanView;

    public BeforePresenter(BeforeBeanView beforeBeanView) {
        this.beforeBeanView = beforeBeanView;
    }

    @Override
    protected void initModel() {
        beforeModel = new BeforeModel();
    }
    public void getDatas(String data){
        beforeModel.getDatas(new BeforeCallBack() {
            @Override
            public void onSuccess(BeforeBean beforeBean) {
                beforeBeanView.onSuccesss(beforeBean);
            }
        },data);
    }
}
