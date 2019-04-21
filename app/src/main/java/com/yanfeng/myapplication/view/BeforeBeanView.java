package com.yanfeng.myapplication.view;


import com.yanfeng.myapplication.base.BaseMvpView;
import com.yanfeng.myapplication.bean.BeforeBean;

public interface BeforeBeanView extends BaseMvpView {
    void onSuccesss(BeforeBean beforeBean);
}
