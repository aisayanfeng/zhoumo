package com.yanfeng.myapplication.presenter;



import android.icu.util.IslamicCalendar;

import com.yanfeng.myapplication.base.BasePresenter;
import com.yanfeng.myapplication.bean.DocumentBean;
import com.yanfeng.myapplication.callback.ResultCallBack;
import com.yanfeng.myapplication.model.BlankModel;
import com.yanfeng.myapplication.view.BlankView;

import java.util.ArrayList;

public class BlankPresenter extends BasePresenter<BlankView> {

    private BlankModel model;

    @Override
    protected void initModel() {
        model = new BlankModel();
        mModels.add(model);
    }
    public void getBlank(String href) {
        model.getBlank(href,new ResultCallBack<ArrayList<DocumentBean>>() {
            @Override
            public void onSuccess(ArrayList<DocumentBean> bean) {
                if (bean!=null){
                    if (mView!=null){
                        mView.updateDoc(bean);
                    }
                }

            }

            @Override
            public void onFail(String msg) {


            }
        });
    }
}
