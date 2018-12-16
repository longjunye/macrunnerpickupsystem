package com.fairymo.macrunnerpickupsystem.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.fairymo.macrunnerpickupsystem.R;
import com.fairymo.macrunnerpickupsystem.views.AnimateFirstDisplayListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public abstract class BaseActivity extends AppCompatActivity {
	@Nullable
	protected Context mContext;
	protected String TAG = getClass().getSimpleName();
	protected ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	@Nullable
	protected ActionBar actionBar;

	protected DisplayImageOptions options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.loading_error)
		.showImageForEmptyUri(R.drawable.loading_error)
		.showImageOnFail(R.drawable.loading_error)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.displayer(new CircleBitmapDisplayer(Color.WHITE, 5))
		.build();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		init();
	}

	protected abstract void init();

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
