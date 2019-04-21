package com.yanfeng.myapplication.model;

import com.yanfeng.myapplication.base.BaseModel;
import com.yanfeng.myapplication.bean.DailyNewsBean;
import com.yanfeng.myapplication.bean.HotBean;
import com.yanfeng.myapplication.callback.ResultCallBack;
import com.yanfeng.myapplication.net.BaseObserver;
import com.yanfeng.myapplication.net.HotService;
import com.yanfeng.myapplication.net.HttpUtils;
import com.yanfeng.myapplication.net.RxUtils;
import com.yanfeng.myapplication.net.ZhihuService;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class HotM extends BaseModel {
    public void getData(final ResultCallBack<HotBean> callBack){
        HotService apiserver = HttpUtils.getInstance().getApiserver(HotService.url, HotService.class);
        Observable<HotBean> observable = apiserver.getHotData();
        observable.compose(RxUtils.<HotBean>rxObserableSchedulerHelper())
               .subscribe(new BaseObserver<HotBean>() {
                   @Override
                   public void onSubscribe(Disposable d) {
                       mCompositeDisposable.add(d);
                   }

                   @Override
                   public void onNext(HotBean hotBean) {
                        callBack.onSuccess(hotBean);
                   }
               });
    }
}
