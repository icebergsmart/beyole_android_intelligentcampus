package com.beyole.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @version 1.0
 * @date 2015/10/18
 * @author Iceberg
 * 
 */
public class ViewpaperAdapter extends PagerAdapter {

	// 初始化传入的view对象
	private List<View> views;
	// 初始化上下文对象
	private Context context;

	public ViewpaperAdapter(List<View> views, Context context) {
		this.views = views;
		this.context = context;
	}

	// 返回adapter的个数
	@Override
	public int getCount() {
		return views.size();
	}

	// 类似于getView方法
	@Override
	public Object instantiateItem(View container, int position) {
		// 向viewpaper中增加view对象
		((ViewPager) container).addView(views.get(position));
		// 返回view对象
		return views.get(position);
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		// 从viewpager中销毁view对象
		((ViewPager) container).removeView(views.get(position));
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// 判断是不是同一个view对象
		return (arg0 == arg1);
	}

}
