package com.yanfeng.myapplication.net;

import com.yanfeng.myapplication.bean.HotBean;
import com.yanfeng.myapplication.bean.SectionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HotService {
    //http://news-at.zhihu.com/api/4/news/hot
    public String url="https://news-at.zhihu.com/api/4/";
    @GET("news/hot")
    Observable<HotBean> getHotData();

    @GET("sections")
    Observable<SectionBean> getSectionData();
}
