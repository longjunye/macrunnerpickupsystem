package com.fairymo.macrunnerpickupsystem;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.webkit.WebView;

import com.fairymo.macrunnerpickupsystem.constants.Constant;
import com.fairymo.macrunnerpickupsystem.utils.SharedPreferencesUtil;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.tencent.tinker.entry.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;

import cn.jpush.android.api.JPushInterface;

public class CallingApplication extends Application {
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
		MultiDex.install(this);
		initTinker();
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
		JPushInterface.setAlias(this, 1008612, alias);
	}

	private void initTinker() {
		ApplicationLike tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();

		// 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
		TinkerPatch.init(tinkerApplicationLike)
			.reflectPatchLibrary()
			.setPatchRollbackOnScreenOff(true)
			.setPatchRestartOnSrceenOff(true)
			.setFetchPatchIntervalByHours(3);

		// 每隔3个小时(通过setFetchPatchIntervalByHours设置)去访问后台时候有更新,通过handler实现轮训的效果
		TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();
	}

}
