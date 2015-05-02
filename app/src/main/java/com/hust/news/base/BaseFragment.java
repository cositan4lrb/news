package com.hust.news.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//所有fragment的基类 包含左侧菜单fragment 和页面fragment

//有一个方法是抽象的 这个class就是抽象的
public abstract class BaseFragment extends Fragment {

	public Activity mActivity;

	// 把fragment绑定到哪个activity上 获取上下文对象提供给OnCreateView方法 以activity取出
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.mActivity = getActivity();
		//从逻辑上来看 这里拿到的mActivity应该是MainUI 因为MainUI中存放了两个Fragment(左侧菜单和主页面)
	}

	// 初始化View方法
	// 子类必须实现此方法 返回一个View参数 作为当前Fragment的布局去展示
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = initView(inflater);

		return view;

	}

	// 将返回当前帧的布局的方法写成抽象方法
	public abstract View initView(LayoutInflater inflater);

	// 当Activity创建完成 执行如下方法（加载数据）
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initData();
	}

	// 可以不初始化数据 所以不用写成抽象方法 如果子类需要初始化数据 可以override
	public void initData() {
		// TODO Auto-generated method stub

	}

}
