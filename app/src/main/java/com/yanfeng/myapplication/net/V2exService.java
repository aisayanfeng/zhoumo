package com.yanfeng.myapplication.net;

import com.yanfeng.myapplication.bean.V2exBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface V2exService {
    String v2exUrl = "https://www.wanandroid.com/";
    String mUrl = "https://www.v2ex.com";
    @GET("navi/json")
    Observable<V2exBean> getV2EXData();
}
