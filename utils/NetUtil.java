package com.fairymo.macrunnerpickupsystem.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.fairymo.macrunnerpickupsystem.CallingApplication;

public class NetUtil {
	/**
	 * 检查当前网络是否可用
	 * @return 是否连接到网络
	 */
	public static boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) CallingApplication.getApp()
			.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivityManager != null) {
			NetworkInfo info = connectivityManager.getActiveNetworkInfo();
			if (info != null && info.isConnected()) {
				return info.getState() == NetworkInfo.State.CONNECTED;
			}
		}
		return false;
	}
}
