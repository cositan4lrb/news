package com.hust.news.base.impl;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hust.news.MainUI;
import com.hust.news.base.LeftMenuDetailBasePager;
import com.hust.news.base.TabBasePager;
import com.hust.news.base.impl.newscenterimpl.InteractMenuDetailPager;
import com.hust.news.base.impl.newscenterimpl.NewsMenuDetailPager;
import com.hust.news.base.impl.newscenterimpl.PhotosMenuDetailPager;
import com.hust.news.base.impl.newscenterimpl.TopicMenuDetailPager;
import com.hust.news.domain.NewsCenterBean;
import com.hust.news.domain.NewsCenterBean.NewsCenterData;
import com.hust.news.fragment.LeftMenuFragment;
import com.hust.news.utils.AddressContext;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * @author dapang 首页的页面
 */
public class NewsCenterPager extends TabBasePager {

	// 要对pagerList操作 将其提取出来
	private ArrayList<LeftMenuDetailBasePager> pagerList;// 左侧菜单页面

	private List<NewsCenterData> leftMenuListData;// 左侧菜单标题的数据
													// 是Bean.data数组中的"title"字段

	public NewsCenterPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	// 注意 此时的继承意味着这个new出来的HomePager已经有了三个控件的布局
	// 现在要重写initData函数 来修改里面的参数
	// 里面的参数 已经被写为成员变量 public 可以被直接访问

	public void initData() {
		tvTitle.setText("新闻");
		//ibMenu.setVisibility(View.VISIBLE);

		// alt+shift+m 将方法抽取出来
		getDataFromNet();

	}

	/**
	 * 抓取数据
	 */
	protected void getDataFromNet() {
		// 去服务端抓取数据 xUtils
		HttpUtils httpUtils = new HttpUtils();
		// 这里第三个参数是回调 因为网络请求是子线程 所以需要对主线程通信 要么是handler 要么是run on UI thread
		// 注意 第三个参数这里封装成request 已经在主线程运行 需要提供抓取的数据的泛型 我们是String
		httpUtils.send(HttpMethod.GET, AddressContext.NEWSCENTER_URL,
				new RequestCallBack<String>() {

					// 两个需要实现的方法 分别在成功和失败后调用
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						System.out.print("新闻中心数据请求失败" + responseInfo.result);

						// 当接收成功 解析json 这里的result就是我们接收的数据 类型取决于泛型
						precessData(responseInfo.result);

					}

					// msg是错误信息
					@Override
					public void onFailure(HttpException error, String msg) {
						System.out.print("新闻中心数据请求失败" + msg);

					}
				});
	}

	/**
	 * 解析和处理json数据 作为菜单栏 用Gson 谷歌的开源框架
	 * 
	 * @param result
	 */
	protected void precessData(String result) {
		Gson gson = new Gson();
		// 要求传回来JavaBean的class 交给Gson框架 它解析后回传JavaBean(已经解析好)
		NewsCenterBean bean = gson.fromJson(result, NewsCenterBean.class);

		// 验证解析是否成功 只需要看看最深的数据是否解析成功
		System.out.print(bean.data.get(0).children.get(0).title);

		// 先去初始化页面数据
		pagerList = new ArrayList<LeftMenuDetailBasePager>();

		pagerList.add(new NewsMenuDetailPager(mContext, bean.data.get(0)));
		pagerList.add(new TopicMenuDetailPager(mContext));
		pagerList.add(new PhotosMenuDetailPager(mContext));
		pagerList.add(new InteractMenuDetailPager(mContext));

		// 左侧菜单的目录来自于data字段 生成固定链接 避免重复易出错
		leftMenuListData = bean.data;
		// 高级操作 Ctrl+shift+O 导包 强转上下文 找到mContext
		LeftMenuFragment leftMenuFragment = ((MainUI) mContext)
				.getLeftMenuFragment();
		// 注意这一步 setMenuListData是建立在页面数据库被初始化的基础上的
		// 所以必须放到页面pageList初始化的后面
		leftMenuFragment.setMenuListData(leftMenuListData);

	}

	/**
	 * @param position
	 *            根据position （点击位置） 切换详情页帧布局的页面
	 */
	public void switchCurrentPager(int position) {

		// 通过position找pager
		LeftMenuDetailBasePager pager = pagerList.get(position);

		// 找到pager以后 一方面将其view对象加到帧布局当中

		View view = pager.getRootView();
		flContent.removeAllViews();
		flContent.addView(view);

		// 另一方面 将标题栏的的标题进行改变
		tvTitle.setText(leftMenuListData.get(position).title);

		// 调用初始化数据方法 initData
		pager.initData();
	}

}
