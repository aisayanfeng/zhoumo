package com.yanfeng.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.yanfeng.myapplication.base.BaseActivity;
import com.yanfeng.myapplication.fragment.AboutFragment;
import com.yanfeng.myapplication.fragment.CollectFragment;
import com.yanfeng.myapplication.fragment.GankFragment;
import com.yanfeng.myapplication.fragment.GoldFragment;
import com.yanfeng.myapplication.fragment.SettingFragment;
import com.yanfeng.myapplication.fragment.V2exFragment;
import com.yanfeng.myapplication.fragment.WechatFragment;
import com.yanfeng.myapplication.fragment.ZhihuDailyNewsFragment;
import com.yanfeng.myapplication.presenter.MainP;
import com.yanfeng.myapplication.view.MainView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainView, MainP> implements MainView {

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    private int mLastFragmentPosition;
    @BindView(R.id.nv)
    NavigationView nv;
    @BindView(R.id.dl)
    DrawerLayout dl;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;
    private ArrayList<Fragment> fragments;
    private ArrayList<Integer> mTitles;
    private FragmentManager mManager;
    private int TYPE_ZHIHU=0;
    private int TYPE_WECHAT=1;
    private int TYPE_GANK=2;
    private int TYPE_GOLD=3;
    private int TYPE_V2EX=4;
    private int TYPE_COLLECT=5;
    private int TYPE_SETTINGS=6;
    private int TYPE_ABOUT=7;
    private MenuItem mSearchItem;


    @Override
    protected MainP initPresenter() {
        return new MainP();
    }

    //艳风  1808D
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mManager = getSupportFragmentManager();
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.about, R.string.about);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        dl.addDrawerListener(toggle);
        toggle.syncState();

        initFragments();
        initTitles();
        addZhihuDailyNewsFragment();
    }

    private void addZhihuDailyNewsFragment() {
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.fragment_container,fragments.get(0));
        transaction.commit();

        toolbar.setTitle(mTitles.get(0));
    }

    private void initTitles() {
        mTitles=new ArrayList<>();
        mTitles.add(R.id.zhihu);
        mTitles.add(R.id.wechat);
        mTitles.add(R.id.gank);
        mTitles.add(R.id.gold);
        mTitles.add(R.id.v2ex);
        mTitles.add(R.id.collect);
        mTitles.add(R.id.settings);
        mTitles.add(R.id.about);
    }

    private void initFragments() {
        fragments=new ArrayList<>();
        fragments.add(new ZhihuDailyNewsFragment());
        fragments.add(new WechatFragment());
        fragments.add(new GankFragment());
        fragments.add(new GoldFragment());
        fragments.add(new V2exFragment());
        fragments.add(new CollectFragment());
        fragments.add(new SettingFragment());
        fragments.add(new AboutFragment());
    }

    @Override
    protected void initListener() {
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if(itemId!=R.id.info_title && itemId!=R.id.options_title){
                    menuItem.setChecked(true);
                    switch (itemId){
                        case R.id.zhihu:
                            switchFragment(TYPE_ZHIHU);
                            break;
                        case R.id.wechat:
                            switchFragment(TYPE_WECHAT);
                            break;
                        case R.id.gank:
                            switchFragment(TYPE_GANK);
                            break;
                        case R.id.gold:
                            switchFragment(TYPE_GOLD);
                            break;
                        case R.id.v2ex:
                            mSearchView.setVisibility(View.GONE);
                            switchFragment(TYPE_V2EX);
                            break;
                        case R.id.collect:
                            switchFragment(TYPE_COLLECT);
                            break;
                        case R.id.settings:
                            switchFragment(TYPE_SETTINGS);
                            break;
                        case R.id.about:
                            switchFragment(TYPE_ABOUT);
                            break;
                    }
                    dl.closeDrawer(Gravity.LEFT);
                }else {
                    menuItem.setChecked(false);
                }
                return false;
            }
        });
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                
            }

            @Override
            public void onSearchViewClosed() {

            }
        });


    }

    private void switchFragment(int type){
        Fragment fragment = fragments.get(type);

        Fragment hideFragment = fragments.get(mLastFragmentPosition);
        FragmentTransaction transaction = mManager.beginTransaction();
        if(!fragment.isAdded()){
            transaction.add(R.id.fragment_container,fragment);
        }

        transaction.hide(hideFragment);
        transaction.show(fragment);
        transaction.commit();

        mLastFragmentPosition=type;

        if(type==TYPE_WECHAT || type ==TYPE_GANK){
            mSearchItem.setVisible(true);
        }else {
            mSearchItem.setVisible(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu , menu);
        mSearchItem = menu.findItem(R.id.acton_search);
        mSearchItem.setVisible(false);
        mSearchView.setMenuItem(mSearchItem);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(mSearchView.isSearchOpen()){
            mSearchView.closeSearch();
        }else {
            super.onBackPressed();
        }
    }
}
