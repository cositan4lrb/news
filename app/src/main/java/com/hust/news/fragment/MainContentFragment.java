package com.hust.news.fragment;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import com.hust.news.MainUI;
import com.hust.news.R;
import com.hust.news.base.BaseFragment;
import com.hust.news.base.TabBasePager;
import com.hust.news.base.impl.GovAffairsPager;
import com.hust.news.base.impl.HomePager;
import com.hust.news.base.impl.NewsCenterPager;
import com.hust.news.base.impl.SettingsPager;
import com.hust.news.base.impl.SmartServicePager;
import com.hust.news.view.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainContentFragment extends BaseFragment implements
		OnCheckedChangeListener {

	@ViewInject(R.id.vp_content_fragment)
	private NoScrollViewPager mViewPager;// 页面布局

	
	public List<TabBasePager> pagerList;// 页面列表

	private View view;

	
	// 利用xUtils的注入方法找到fragment
	// 这里单独写了个屏蔽滑动的类

	/*
	 * (non-Javadoc) 主界面的Fragment 打气筒
	 */
	@Override
	public View initView(LayoutInflater inflater) {

		view = inflater.inflate(R.layout.content_fragment, null);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void initData() {
		pagerList = new ArrayList<TabBasePager>();
		pagerList.add(new HomePager(mActivity));
		pagerList.add(new NewsCenterPager(mActivity));
		pagerList.add(new SmartServicePager(mActivity));
		pagerList.add(new GovAffairsPager(mActivity));
		pagerList.add(new SettingsPager(mActivity));

		// 先new出来adapter
		ContentAdapter mAdapter = new ContentAdapter();
		// 再把viewpager和adapter进行绑定
		mViewPager.setAdapter(mAdapter);

		// 设置一个默认选中 是主页

		// 第零个页面开始加载数据 首页
		pagerList.get(0).initData();

		// 首页也不可以滑动
		((MainUI) mActivity).getSlidingMenu().setTouchModeAbove(
				SlidingMenu.TOUCHMODE_NONE);

		// 对radiobutton设置选中改变的监听

	};



	
	// 调用适配器 将以上元素显示在帧布局当中
	class ContentAdapter extends PagerAdapter {

		// 适配器经典四步

		@Override
		public int getCount() {
			// 将数据提供的大小传进来 需要将数据提供者先写成成员变量
			return pagerList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		// 右键 Source override 重写基类
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// 通过position查到view 然后加载进container
			TabBasePager pager = pagerList.get(position);
			// 抓来rootview
			View rootView = pager.getRootView();

			// 插入重要一步 别忘了initData 通过刚抓取到的pager
			// pager.initData();

			// container 进行加载
			container.addView(rootView);
			// 再把rootView进行返回
			return rootView;
		}

	}

	// 左侧菜单拿到MainUI对象后 需要继续深入下一层拿到新闻中心 作为主页面 新闻中心页面在pagerlist中出在编号1
	public NewsCenterPager getNewsCenterPager() {
		return (NewsCenterPager) pagerList.get(1);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub

	}

}
