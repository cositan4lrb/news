package com.hust.news;

import com.hust.news.utils.CacheUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

public class WelcomeUI extends Activity implements AnimationListener {

	
	//定义公开的文件存储打开信息 名为IS_OPEN_MAIN_PAGER
	public static final String IS_OPEN_MAIN_PAGER = "flag1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);

		init();
	}

	private void init() {
		View rootView = findViewById(R.id.rl_welcome_root);

		// 旋转360 时间一秒
		RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotateAnimation.setDuration(1000);
		// 动作结束后保持
		rotateAnimation.setFillAfter(true);

		// 放大0~1 时间一秒
		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		scaleAnimation.setDuration(1000);
		scaleAnimation.setFillAfter(true);

		// 渐变0~1 时间两秒
		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(2000);
		alphaAnimation.setFillAfter(true);

		// 把三个动画加入动画序列
		AnimationSet animSet = new AnimationSet(false);
		animSet.addAnimation(rotateAnimation);
		animSet.addAnimation(scaleAnimation);
		animSet.addAnimation(alphaAnimation);
		animSet.setAnimationListener(this);
       
		//开启三个动画连播
		rootView.startAnimation(animSet);


	}

	// 动画执行完毕后 进入引导界面 如果之前已经进入过主界面 就不进入引导界面

	// 当监听到动画结束时 执行以下程序

	@Override
	public void onAnimationEnd(Animation animation) {
		
		// 去文件中取是否打开过文件的值
		boolean isOpenMainPager = CacheUtils.getBoolean(WelcomeUI.this,
				IS_OPEN_MAIN_PAGER, false);
		
		//将intent抽出 intent无论何种条件都被执行 只是class不同
		Intent intent = new Intent();
		if (isOpenMainPager) {
			// 已经打开过一次主界面，直接进入主页面
			intent.setClass(WelcomeUI.this, MainUI.class);
		} else {
			// 没有打开过主界面，进入引导页面
			intent.setClass(WelcomeUI.this, GuideUI.class);
		}
		startActivity(intent);
		
		//进入以后 关闭掉欢迎页面 否则点返回会呈现欢迎页面
		finish();

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}



}
