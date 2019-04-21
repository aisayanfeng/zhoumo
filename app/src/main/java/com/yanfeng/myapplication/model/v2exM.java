package com.yanfeng.myapplication.model;

import android.support.v4.app.Fragment;

import com.yanfeng.myapplication.base.BaseModel;
import com.yanfeng.myapplication.callback.MyCallBack;
import com.yanfeng.myapplication.callback.ResultCallBack;
import com.yanfeng.myapplication.fragment.BlankFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class v2exM extends BaseModel {
    private String mUrl="https://www.v2ex.com/";
    private ArrayList<String> titles;
    private ArrayList<Fragment> fragments;

    public void getV2ex(final MyCallBack callBack){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    fragments=new ArrayList<>();
                    titles=new ArrayList<>();
                    Document document = Jsoup.connect(mUrl).get();
                    if(document!=null){
                        Elements tabs = document.select("div#Tabs.inner > a");
                        for (Element element:tabs) {
                            String text = element.text();
                            String href = element.attr("href");
                            titles.add(text);
                            fragments.add(BlankFragment.newInstant(href));
                        }

                        callBack.onMySuccess(titles,fragments);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
