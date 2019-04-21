package com.yanfeng.myapplication.model;

import com.yanfeng.myapplication.base.BaseModel;
import com.yanfeng.myapplication.bean.HotBean;
import com.yanfeng.myapplication.bean.SectionBean;
import com.yanfeng.myapplication.callback.ResultCallBack;
import com.yanfeng.myapplication.net.BaseObserver;
import com.yanfeng.myapplication.net.HotService;
import com.yanfeng.myapplication.net.HttpUtils;
import com.yanfeng.myapplication.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class SectionM extends BaseModel {
    public void getData(final ResultCallBack<SectionBean> callBack){
        HotService apiserver = HttpUtils.getInstance().getApiserver(HotService.url, HotService.class);
        Observable<SectionBean> observable = apiserver.getSectionData();
        observable.compose(RxUtils.<SectionBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<SectionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(SectionBean bean) {
                        callBack.onSuccess(bean);
                    }
                });
    }
}
