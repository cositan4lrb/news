package com.hust.news.base.impl.newscenterimpl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hust.news.MainUI;
import com.hust.news.R;
import com.hust.news.base.LeftMenuDetailBasePager;
import com.hust.news.domain.NewsCenterBean.ChildRen;
import com.hust.news.domain.NewsCenterBean.NewsCenterData;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.TabPageIndicator;

/**
 * @author dapang 新闻菜单对应的页面
 */
public class NewsMenuDetailPager extends LeftMenuDetailBasePager implements ViewPager.OnPageChangeListener {

    // 利用Utils框架找到新闻中心页面的三个控件 顶部滑动菜单 旁边的按钮 以及下方的页面
    @ViewInject(R.id.tpi_news_menu)
    private TabPageIndicator mIndicator;

    @ViewInject(R.id.ib_news_next_tab)
    private ImageButton ibNextTabButton;

    @ViewInject(R.id.vp_news_menu_content)
    private ViewPager mViewPager;

    // 当前页面的页签的数据
    private List<ChildRen> childrenList;
    private List<NewsMenuTabDetailPager> tabPagerList; // 页签详情页面

    private View view;

    // 那个方法new这个这早函数 就找谁要数据 Ctrl+Alt+H 可以查看调用关系
    public NewsMenuDetailPager(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    // 修改构造函数 必须继承父类里的方法 用super继承 注意形参的统一
    public NewsMenuDetailPager(Context context, NewsCenterData newsCenterData) {
        // TODO Auto-generated constructor stub
        super(context);

        // 接收数据 存成List 并且设置为Field 方便适配器调用
        childrenList = newsCenterData.children;

    }

    @Override
    public View initView() {

        view = View.inflate(mContext, R.layout.news_menu, null);
        ViewUtils.inject(this, view);

        return view;
    }

    @Override
    public void initData() {
        // TODO Auto-generated method stub

        // 把页签对应的详情页面准备出来.
        tabPagerList = new ArrayList<NewsMenuTabDetailPager>();
        for (int i = 0; i < childrenList.size(); i++) {
            tabPagerList.add(new NewsMenuTabDetailPager(mContext, childrenList.get(i)));
        }

        // 思维不要混乱 先是给这个页面添加数据适配器
        NewsPagerAdapter mAdapter = new NewsPagerAdapter();
        mViewPager.setAdapter(mAdapter);

        // ViewsIndicator这个框架就是为了viewPager天生设计的 绑定后即可使用
        mIndicator.setViewPager(mViewPager);
        mIndicator.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        isEnableSlidingMenu(position == 0);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    // 永远记得 新的页面绑定数据 第一步就是写个继承父类的数据适配器 提供四个基本方法 根据需要增加方法
    class NewsPagerAdapter extends PagerAdapter {

        private TextView mTextView;

        @Override
        public int getCount() {

            return childrenList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            // TODO Auto-generated method stub
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            NewsMenuTabDetailPager pager = tabPagerList.get(position);
            View rootView = pager.getRootView();
            container.addView(rootView);

            // 初始化数据
            pager.initData();
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
            container.removeView((View) object);
        }

        // 这个方法不属于适配器四步走里的 意义在于 可以实现底部ViewPager随着顶部标题联动的效果
        @Override
        public CharSequence getPageTitle(int position) {
            // TODO Auto-generated method stub
            return childrenList.get(position).title;
        }

    }

    private void isEnableSlidingMenu(boolean isEnable) {
        if(isEnable) {
            ((MainUI) mContext).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            ((MainUI) mContext).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }

}
