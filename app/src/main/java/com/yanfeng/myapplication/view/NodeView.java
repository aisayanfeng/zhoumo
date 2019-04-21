package com.yanfeng.myapplication.view;


import com.yanfeng.myapplication.base.BaseMvpView;
import com.yanfeng.myapplication.bean.V2exBean;

import java.util.List;

public interface NodeView extends BaseMvpView {
    void updateNode(List<V2exBean.DataBean> data);
}
