package com.hust.news.base;

import com.hust.news.R;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

//写一个基类 为标签页面做模板
/**
 * @author dapang
 *
 */
public class TabBasePager {

	public Context mContext;
	// 改成pubic方便子集调用 毕竟写的是抽象类
	public FrameLayout flContent;
	public TextView tvTitle;
	public ImageButton ibMenu;
	private View rootView;


	// new必须知道上下文 根据上下文得知所在布局
	public TabBasePager(Context context) {

		// 得到上下文
		this.mContext = context;
		// 这相当于第三层
		rootView = initView();

	}

	// 对于每一个tab页面都需要加载三个控件 左侧菜单 上部标题 和左上角按钮
	private View initView() {
		View view = View.inflate(mContext, R.layout.tab_base_pager, null);

		
		
		// 找到Ctrl+2 L 之后Ctrl+1 加入成员变量
		// 三个控件 标题栏文字 帧布局内容 下面的按钮组
		tvTitle = (TextView) view.findViewById(R.id.tv_title_bar_title);
		flContent = (FrameLayout) view
				.findViewById(R.id.fl_tab_base_pager_content);
		ibMenu = (ImageButton) view.findViewById(R.id.ib_title_bar_menu);

		return view;

	}

	/**
	 * 得到当前页面布局对象
	 * 
	 * @return
	 */
	public View getRootView() {
		return rootView;
	}

	/**
	 * 写一个空方法 叫获取数据 会被子类重写
	 */
	public void initData() {

	}

}
