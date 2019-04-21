package com.yanfeng.myapplication.model;

import com.yanfeng.myapplication.base.BaseModel;
import com.yanfeng.myapplication.bean.DailyNewsBean;
import com.yanfeng.myapplication.bean.WeChatBean;
import com.yanfeng.myapplication.callback.ResultCallBack;
import com.yanfeng.myapplication.net.BaseObserver;
import com.yanfeng.myapplication.net.HttpUtils;
import com.yanfeng.myapplication.net.RxUtils;
import com.yanfeng.myapplication.net.WeChatService;
import com.yanfeng.myapplication.net.ZhihuService;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class WeChatM extends BaseModel {
    public void getData(final ResultCallBack<WeChatBean> callBack){
        WeChatService apiserver = HttpUtils.getInstance().getApiserver(WeChatService.url, WeChatService.class);
        Observable<WeChatBean> observable = apiserver.getWeChat();
        observable.compose(RxUtils.<WeChatBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WeChatBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WeChatBean bean) {
                        callBack.onSuccess(bean);
                    }
                });
    }
}
