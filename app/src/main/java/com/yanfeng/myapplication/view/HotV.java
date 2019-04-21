package com.yanfeng.myapplication.view;

import com.yanfeng.myapplication.base.BaseMvpView;
import com.yanfeng.myapplication.bean.DailyNewsBean;
import com.yanfeng.myapplication.bean.HotBean;

public interface HotV extends BaseMvpView {
    void setData(HotBean bean);
}
