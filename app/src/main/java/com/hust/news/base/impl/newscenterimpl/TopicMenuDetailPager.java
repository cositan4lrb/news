package com.hust.news.base.impl.newscenterimpl;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.hust.news.base.LeftMenuDetailBasePager;

/**
 * @author dapang 新闻菜单对应的页面
 */
public class TopicMenuDetailPager extends LeftMenuDetailBasePager {

	public TopicMenuDetailPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		TextView tvTextView = new TextView(mContext);
		tvTextView.setText("专题菜单页面");
		tvTextView.setTextColor(Color.RED);
		tvTextView.setGravity(Gravity.CENTER);
		tvTextView.setTextSize(23);


		
		
		return tvTextView;
	}

}
