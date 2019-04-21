package com.yanfeng.myapplication.view;

import android.support.v4.app.Fragment;

import com.yanfeng.myapplication.base.BaseMvpView;

import java.util.ArrayList;

public interface V2exV extends BaseMvpView {
    void setAllData(ArrayList<String> titles, ArrayList<Fragment> fragments);
}
