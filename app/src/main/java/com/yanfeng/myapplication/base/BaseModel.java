package com.yanfeng.myapplication.base;

import io.reactivex.disposables.CompositeDisposable;

public class BaseModel {
    protected CompositeDisposable mCompositeDisposable=new CompositeDisposable();
    public void onDestory(){
        mCompositeDisposable.clear();
    }
}
