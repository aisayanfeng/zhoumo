package com.yanfeng.myapplication.view;

import com.yanfeng.myapplication.base.BaseMvpView;
import com.yanfeng.myapplication.bean.DailyNewsBean;

public interface DailyNewsV extends BaseMvpView {
    void setData(DailyNewsBean bean);
}
