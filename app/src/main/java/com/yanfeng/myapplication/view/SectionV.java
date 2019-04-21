package com.yanfeng.myapplication.view;

import com.yanfeng.myapplication.base.BaseMvpView;
import com.yanfeng.myapplication.bean.HotBean;
import com.yanfeng.myapplication.bean.SectionBean;

public interface SectionV extends BaseMvpView {
    void setData(SectionBean bean);
}
