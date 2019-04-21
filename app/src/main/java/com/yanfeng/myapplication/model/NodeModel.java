package com.yanfeng.myapplication.model;



import com.yanfeng.myapplication.base.BaseModel;
import com.yanfeng.myapplication.bean.V2exBean;
import com.yanfeng.myapplication.callback.ResultCallBack;
import com.yanfeng.myapplication.net.BaseObserver;
import com.yanfeng.myapplication.net.HttpUtils;
import com.yanfeng.myapplication.net.RxUtils;
import com.yanfeng.myapplication.net.V2exService;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class NodeModel extends BaseModel {
    public void getNode(final ResultCallBack callBack) {
        V2exService apiserver = HttpUtils.getInstance().getApiserver(V2exService.v2exUrl, V2exService.class);
        Observable<V2exBean> observable = apiserver.getV2EXData();
        observable.compose(RxUtils.<V2exBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<V2exBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(V2exBean v2exBean) {
                        if (v2exBean.getErrorCode()==0){
                            callBack.onSuccess(v2exBean);
                        }else {
                            callBack.onFail("请求失败");
                        }
                    }
                });
    }
}
