package com.hust.news;

import java.util.ArrayList;
import java.util.List;

import com.hust.news.R.layout;
import com.hust.news.utils.CacheUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;


public class GuideUI extends Activity implements OnPageChangeListener,
		OnClickListener {


	private List<ImageView> imageViewList; 
	private LinearLayout llPointGroup; 
	private View mSelectPoint; 
	private int basicWidth; 
	private View btnStartExperienceButton; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(layout.guide);
		initView();

	}


	private void initView() {
		ViewPager mViewPager = (ViewPager) findViewById(R.id.vp_guide);
		btnStartExperienceButton = (Button) findViewById(R.id.btn_guide_start_experience);
		llPointGroup = (LinearLayout) findViewById(R.id.ll_guide_point_group);
		mSelectPoint = findViewById(R.id.select_point);

		initData();
		GuideAdapter mAdapter = new GuideAdapter();


		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(this);
		btnStartExperienceButton.setOnClickListener(this);


		mSelectPoint.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {

		
						mSelectPoint.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);

			
						basicWidth = llPointGroup.getChildAt(1).getLeft()
								- llPointGroup.getChildAt(0).getLeft();
						
					}
				});

	}


	private void initData() {
		int[] imageResIDs = { R.drawable.guide_1, R.drawable.guide_2,
				R.drawable.guide_3 };

	
		imageViewList = new ArrayList<ImageView>();

		
		ImageView iv;
		View view;
		LayoutParams params;

		for (int i = 0; i < imageResIDs.length; i++) {
			iv = new ImageView(this);
			iv.setBackgroundResource(imageResIDs[i]);
			imageViewList.add(iv);

	
			view = new View(this);


			params = new LayoutParams(30, 30);

	
			if (i != 0) {
				params.leftMargin = 20;
			}
			view.setBackgroundResource(R.drawable.point_normal);
			view.setLayoutParams(params);
			llPointGroup.addView(view);
		}

	}

	class GuideAdapter extends PagerAdapter {

		@Override
		public int getCount() {

			return imageViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {


			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
	
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView iv = imageViewList.get(position);


			container.addView(iv);

	
			return iv;
		}

	}


	@Override
	public void onPageScrolled(int position, float positionOffset,
							   int positionOffsetPixels) {

		int leftMargin = (int) (basicWidth * (position + positionOffset));

	
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mSelectPoint
				.getLayoutParams();
		params.leftMargin = leftMargin;
		mSelectPoint.setLayoutParams(params);
	}


	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub

	
		if (position == imageViewList.size() - 1.) {
			btnStartExperienceButton.setVisibility(View.VISIBLE);
		} else {

			btnStartExperienceButton.setVisibility(View.GONE);
		}

	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}


	@Override
	public void onClick(View v) {
	
		CacheUtils.putBoolean(this, WelcomeUI.IS_OPEN_MAIN_PAGER, true);


		startActivity(new Intent(this, MainUI.class));


		finish();

	}

}
