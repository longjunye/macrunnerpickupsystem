package com.fairymo.macrunnerpickupsystem;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;
import android.webkit.WebView;
import cn.jpush.android.api.JPushInterface;
import com.fairymo.macrunnerpickupsystem.constants.Constant;
import com.fairymo.macrunnerpickupsystem.utils.SharedPreferencesUtil;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;

public class CallingApplication extends MultiDexApplication {
	@NonNull
	private final String TAG = getClass().getSimpleName();
	private static Application app;

	@NonNull
	public static Application getApp() {
		return app;
	}

	private static void setApp(@NonNull Application app) {
		CallingApplication.app = app;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		setApp(this);
		initJpush();
		initImageLoader();
		if (BuildConfig.DEBUG) {
			WebView.setWebContentsDebuggingEnabled(true);
		}
	}

	private void initImageLoader() {
		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		config.denyCacheImageMultipleSizesInMemory();
		config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
		config.diskCacheSize(50 * 1024 * 1024);
		config.imageDecoder(new BaseImageDecoder(true));
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		if (BuildConfig.DEBUG) {
			config.writeDebugLogs();
		}
		ImageLoader.getInstance().init(config.build());
	}

	private void initJpush() {
		JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
		JPushInterface.init(this);            // 初始化 JPush
		String alias = SharedPreferencesUtil.getString(CallingApplication.getApp(),
			Constant.BRAND_NO, Constant.DEFAULT_BRAND_NO) + SharedPreferencesUtil.getString(CallingApplication.getApp(),
			Constant.SHOPPING_NO, Constant.DEFAULT_SHOPPING_NO) + "PickupSystem";
		JPushInterface.setAlias(getApplicationContext(), 1008612, alias);
	}
}
