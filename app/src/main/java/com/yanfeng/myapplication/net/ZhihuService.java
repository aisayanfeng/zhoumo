package com.yanfeng.myapplication.net;

import com.yanfeng.myapplication.bean.BeforeBean;
import com.yanfeng.myapplication.bean.DailyNewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ZhihuService {
    public String sBaseUrl="https://news-at.zhihu.com/api/4/";
    @GET("news/latest")
    Observable<DailyNewsBean> getLastDailyNews();


    @GET("")
    Observable<BeforeBean> getDatas4(@Url String url);
}
