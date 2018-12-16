package com.fairymo.macrunnerpickupsystem.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

public class SharedPreferencesUtil {
	public static String getString(Context context, String key,
		final String defaultValue) {
		if (context == null || StringUtil.isEmpty(key)) {
			return defaultValue;
		}
		SharedPreferences settings = PreferenceManager
			.getDefaultSharedPreferences(context);
		return settings.getString(key, defaultValue);
	}

	public static void setString(Context context, final String key,
		final String value) {
		if (context == null || StringUtil.isEmpty(key)) {
			return;
		}
		SharedPreferences settings = PreferenceManager
			.getDefaultSharedPreferences(context);
		settings.edit().putString(key, value).apply();
	}

	public static boolean getBoolean(Context context, final String key,
		final boolean defaultValue) {
		if (context == null || StringUtil.isEmpty(key)) {
			return defaultValue;
		}
		SharedPreferences settings = PreferenceManager
			.getDefaultSharedPreferences(context);
		return settings.getBoolean(key, defaultValue);
	}

	public static boolean hasKey(Context context, final String key) {
		return !(context == null || StringUtil.isEmpty(key)) && PreferenceManager.getDefaultSharedPreferences(context).contains(key);
	}

	public static void setBoolean(Context context, final String key,
		final boolean value) {
		if (context == null || StringUtil.isEmpty(key)) {
			return;
		}
		SharedPreferences settings = PreferenceManager
			.getDefaultSharedPreferences(context);
		settings.edit().putBoolean(key, value).apply();
	}

	public static void setInt(Context context, final String key,
		final int value) {
		if (context == null || StringUtil.isEmpty(key)) {
			return;
		}
		SharedPreferences settings = PreferenceManager
			.getDefaultSharedPreferences(context);
		settings.edit().putInt(key, value).apply();
	}

	public static int getInt(Context context, final String key,
		final int defaultValue) {
		if (context == null || StringUtil.isEmpty(key)) {
			return defaultValue;
		}
		SharedPreferences settings = PreferenceManager
			.getDefaultSharedPreferences(context);
		return settings.getInt(key, defaultValue);
	}

	public static void setFloat(Context context, final String key,
		final float value) {
		if (context == null || StringUtil.isEmpty(key)) {
			return;
		}
		SharedPreferences settings = PreferenceManager
			.getDefaultSharedPreferences(context);
		settings.edit().putFloat(key, value).apply();
	}

	public static float getFloat(Context context, final String key,
		final float defaultValue) {
		if (context == null || StringUtil.isEmpty(key)) {
			return defaultValue;
		}
		SharedPreferences settings = PreferenceManager
			.getDefaultSharedPreferences(context);
		return settings.getFloat(key, defaultValue);
	}

	public static void setLong(Context context, final String key,
		final long value) {
		if (context == null || StringUtil.isEmpty(key)) {
			return;
		}
		SharedPreferences settings = PreferenceManager
			.getDefaultSharedPreferences(context);
		settings.edit().putLong(key, value).apply();
	}

	public static long getLong(Context context, final String key,
		final long defaultValue) {
		if (context == null || StringUtil.isEmpty(key)) {
			return defaultValue;
		}
		SharedPreferences settings = PreferenceManager
			.getDefaultSharedPreferences(context);
		return settings.getLong(key, defaultValue);
	}

	public static void clear(@NonNull Context context) {
		SharedPreferences.Editor editor = PreferenceManager
			.getDefaultSharedPreferences(context).edit();
		editor.clear();
		editor.apply();
	}
}
