package com.hust.news.fragment;

import java.util.List;

import com.hust.news.MainUI;
import com.hust.news.R;
import com.hust.news.base.BaseFragment;
import com.hust.news.base.impl.NewsCenterPager;
import com.hust.news.domain.NewsCenterBean;
import com.hust.news.domain.NewsCenterBean.NewsCenterData;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.R.color;
import android.R.drawable;
import android.R.integer;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

//这一页牵扯到特别多的层之间通信 核心在于上下文 mActivity = MainUI 是是一切的中转站 需要用到强制类型转换
//通过上下文去找某元素 当前上下文一定要有提供寻找的方法 否则找不到

public class LeftMenuFragment extends BaseFragment implements
		OnItemClickListener {

	// 定义出来接收菜单条目名称变量
	private List<NewsCenterData> mMenuListData;
	private ListView mListView;
	private MenuAdapter mAdapter;
	private int selectedPosition; // 默认选中的才菜单选项
	private SlidingMenu slidingMenu;

	/*
	 * (non-Javadoc) 左侧菜单的Fragment
	 */
	@Override
	public View initView(LayoutInflater inflater) {

		// 因为需要把新创建的布局传给打气筒 所以把它添加到Field
		mListView = new ListView(mActivity);
		// 细调listview样式 cachecolor做成透明 分割线高为0 背景色为黑
		mListView.setCacheColorHint(Color.TRANSPARENT);
		mListView.setDividerHeight(0);
		// 这里Color里的C要大写！！才是安卓自定义的颜色库
		mListView.setBackgroundColor(Color.BLACK);
		// 注意这个写法
		mListView.setSelector(color.transparent);
		// 控制listview的上下左右的留白大小
		mListView.setPadding(0, 200, 0, 0);

		// 为listView设置点击监听
		mListView.setOnItemClickListener(this);
		return mListView;
	}

	// 写一个方法用于从NewsCenter接收数据
	public void setMenuListData(List<NewsCenterData> menuListData) {

		// 定义一个接收数据的变量
		this.mMenuListData = menuListData;
		// 先把adapter new出来 再进行核绑定
		mAdapter = new MenuAdapter();
		mListView.setAdapter(mAdapter);

		// 默认选中第一个
		selectedPosition = 0;

		// 统一方法 根据selector来选择显示的页面
		switchNewsCenterContentPager();
	}

	private void switchNewsCenterContentPager() {
		// 同时 详情页也要切换到默认选中的页面
		MainUI mainUI = (MainUI) mActivity;
		MainContentFragment mainContentFragment = mainUI
				.getMainContentFragment();
		NewsCenterPager newsCenterPager = mainContentFragment
				.getNewsCenterPager();
		// 注意 当找到NewsCenterPager以后 我们就可以调用其中的switch方法 来切换次级页面
		newsCenterPager.switchCurrentPager(selectedPosition);
	}

	public class MenuAdapter extends BaseAdapter {

		// 打气筒经典四步 必须牢记！！最后返回view 极其容易忘！
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mMenuListData.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			TextView view = null;
			if (convertView == null) {
				view = (TextView) View.inflate(mActivity,
						R.layout.left_menu_item, null);

			} else {
				view = (TextView) convertView;

			}

			// 找到position位置 对应的数据
			view.setText(mMenuListData.get(position).title);

			// 初始的selectedPosition是0 那么当打气筒打到0位时 正好将enable置一 实现默认选第一个的功能
			view.setEnabled(selectedPosition == position);

			// 一定别忘了再把view传回去！！！
			return view;

		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget
	 * .AdapterView, android.view.View, int, long) 注意
	 * 这里的整形就是listview中条目的position 一共四个 分别是0/1/2/3 点击 id就是控件的id
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long id) {
		// TODO Auto-generated method stub

		selectedPosition = position;
		// 只要选中改变 就刷新adapter
		mAdapter.notifyDataSetChanged();

		// 点完之后 把leftMenu收回去
		SlidingMenu slidingMenu = ((MainUI) mActivity).getSlidingMenu();
		// 获取到slidingmenu控制器菜单 调用toggle方法 如果fragemnt打开 则关闭 如果关闭 则打开
		slidingMenu.toggle();

		// 同时 把相应页面的中间部分 要显示对应position的页面
		switchNewsCenterContentPager();

	}

}
