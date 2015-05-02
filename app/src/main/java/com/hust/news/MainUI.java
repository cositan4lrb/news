package com.hust.news;

import java.util.ArrayList;
import java.util.List;

import com.hust.news.fragment.LeftMenuFragment;
import com.hust.news.fragment.MainContentFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingPreferenceActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;


public class MainUI extends SlidingFragmentActivity {


    // 左侧菜单fragment的tag
    private final String LEFT_MENU_FRAGMENT_TAG = "left_menu";
    // 主界面fragment的tag
    private final String MAIN_CONTENT_FRAGMENT_TAG = "main_content";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 主界面去掉标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_content); // 主界面布局
        setBehindContentView(R.layout.left_menu); // 左侧菜单布局

        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setMode(SlidingMenu.LEFT); // 设置左侧菜单可用.
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN); // 整个屏幕都可以拖拽出菜单.
        slidingMenu.setBehindOffset(200); // 设置主界面留在屏幕上的宽度

        initFragment();

    }

    /**
     * 初始化菜单和主界面Fragment
     */
    private void initFragment() {
        // 获取Fragment管理器对象
        FragmentManager fm = getSupportFragmentManager();

        // 开启事物
        FragmentTransaction ft = fm.beginTransaction(); // 得到事物操作对象

        // 替换左侧菜单布局
        ft.replace(R.id.fl_left_menu, new LeftMenuFragment(),
                LEFT_MENU_FRAGMENT_TAG);
        // 替换主界面布局
        ft.replace(R.id.fl_main_content, new MainContentFragment(),
                MAIN_CONTENT_FRAGMENT_TAG);

        // 提交
        ft.commit();
    }

    /**
     * 为处理数据提供方找到左侧菜单的方法
     *
     * @return
     */
    public LeftMenuFragment getLeftMenuFragment() {

        FragmentManager fm = getSupportFragmentManager();
        Fragment leftMenuFragment = fm
                .findFragmentByTag(LEFT_MENU_FRAGMENT_TAG);
        return (LeftMenuFragment) leftMenuFragment;

    }

    /**
     * 为设置初始页面切换提供获取主页面的方法
     *
     * @return
     */
    public MainContentFragment getMainContentFragment() {

        FragmentManager fm = getSupportFragmentManager();
        Fragment mainContentFragment = fm
                .findFragmentByTag(MAIN_CONTENT_FRAGMENT_TAG);
        return (MainContentFragment) mainContentFragment;

    }

}