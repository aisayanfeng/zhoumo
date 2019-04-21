package com.yanfeng.myapplication.model;



import com.yanfeng.myapplication.base.BaseModel;
import com.yanfeng.myapplication.bean.BeforeBean;
import com.yanfeng.myapplication.net.BeforeCallBack;
import com.yanfeng.myapplication.net.ZhihuService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BeforeModel extends BaseModel {
    public void getDatas(final BeforeCallBack callBack, String data){
        Retrofit build = new Retrofit.Builder().baseUrl(ZhihuService.sBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ZhihuService zhiHuService = build.create(ZhihuService.class);
        Observable<BeforeBean> datas4 = zhiHuService.getDatas4("news/before/" + data);
        datas4.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BeforeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BeforeBean beforeBean) {
                        callBack.onSuccess(beforeBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
