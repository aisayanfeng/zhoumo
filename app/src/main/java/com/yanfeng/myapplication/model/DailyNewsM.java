package com.yanfeng.myapplication.model;

import com.yanfeng.myapplication.base.BaseModel;
import com.yanfeng.myapplication.bean.DailyNewsBean;
import com.yanfeng.myapplication.callback.ResultCallBack;
import com.yanfeng.myapplication.net.BaseObserver;
import com.yanfeng.myapplication.net.HttpUtils;
import com.yanfeng.myapplication.net.RxUtils;
import com.yanfeng.myapplication.net.ZhihuService;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class DailyNewsM extends BaseModel {
    public void getData(final ResultCallBack<DailyNewsBean> callBack){
        ZhihuService apiserver = HttpUtils.getInstance().getApiserver(ZhihuService.sBaseUrl, ZhihuService.class);
        Observable<DailyNewsBean> observable = apiserver.getLastDailyNews();
        observable.compose(RxUtils.<DailyNewsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DailyNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DailyNewsBean dailyNewsBean) {
                        callBack.onSuccess(dailyNewsBean);
                    }
                });
    }
}
