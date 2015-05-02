package com.hust.news.base.impl;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.hust.news.base.TabBasePager;

/**
 * @author dapang 首页的页面
 */
public class GovAffairsPager extends TabBasePager {

	public GovAffairsPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	// 注意 此时的继承意味着这个new出来的HomePager已经有了三个控件的布局
	// 现在要重写initData函数 来修改里面的参数
	// 里面的参数 已经被写为成员变量 public 可以被直接访问

	public void initData() {
		tvTitle.setText("人口管理");
		//smMenu.setVisibility(View.GONE);

		// 不管内容 先显示一个文字
		TextView tvTextView = new TextView(mContext);
		tvTextView.setText("政务");
		tvTextView.setTextSize(25);
		tvTextView.setTextColor(Color.RED);
		tvTextView.setGravity(Gravity.CENTER);
		// 在帧布局中加入文本框
		flContent.addView(tvTextView);

	}

}
