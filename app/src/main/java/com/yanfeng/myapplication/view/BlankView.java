package com.yanfeng.myapplication.view;


import com.yanfeng.myapplication.base.BaseMvpView;
import com.yanfeng.myapplication.bean.DocumentBean;

import java.util.ArrayList;

public interface BlankView extends BaseMvpView {
    void updateDoc(ArrayList<DocumentBean> bean);
}
