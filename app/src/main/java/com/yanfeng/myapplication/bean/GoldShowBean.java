package com.yanfeng.myapplication.bean;

import java.io.Serializable;

public class GoldShowBean implements Serializable {
    public String title;
    public boolean isChcked;

    public GoldShowBean(String title, boolean isChcked) {
        this.title = title;
        this.isChcked = isChcked;
    }
}
