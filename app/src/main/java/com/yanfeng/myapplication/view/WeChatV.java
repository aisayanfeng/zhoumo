package com.yanfeng.myapplication.view;

import com.yanfeng.myapplication.base.BaseMvpView;
import com.yanfeng.myapplication.bean.WeChatBean;

public interface WeChatV extends BaseMvpView {
    void setData(WeChatBean bean);
}
