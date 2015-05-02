package com.hust.news.base;

import android.content.Context;
import android.view.View;

/**
 * @author dapang
 *  左侧菜单对应页面的基类
 */
public abstract class LeftMenuDetailBasePager {

	// 在声明任何新页面之前 一定要记得吧上下文加入Field 并且取出来
	public Context mContext;
	private View rootView;

	public LeftMenuDetailBasePager(Context context) {

		this.mContext = context;
		rootView = initView();

	}

	// initView写成抽象方法 让子类去重写
	public abstract View initView();

	// 获取当前绘制节点
	public View getRootView() {
		return rootView;
	}

	/**
	 * 子类需要时就调用本方法 不需要的时候就不调用
	 */
	public void initData() {

	}

}
