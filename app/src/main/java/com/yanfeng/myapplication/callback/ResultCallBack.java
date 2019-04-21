package com.yanfeng.myapplication.callback;

public interface ResultCallBack<T> {
    void onSuccess(T bean);
    void onFail(String msg);

}
