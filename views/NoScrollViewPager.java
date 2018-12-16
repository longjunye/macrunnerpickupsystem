package com.fairymo.macrunnerpickupsystem.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ricky.ye on 6/20/17.
 */

public class NoScrollViewPager extends ViewPager {
	private boolean isScroll;

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollViewPager(Context context) {
		super(context);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return isScroll && super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return !isScroll || super.onTouchEvent(ev);
	}

	public void setScroll(boolean scroll) {
		isScroll = scroll;
	}
}
