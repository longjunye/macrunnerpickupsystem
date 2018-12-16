package com.fairymo.macrunnerpickupsystem.utils;

import android.content.Context;
import android.net.Uri;

public class SchemeHandler {
	private static final String SCHEME = "fairymo";

	public static boolean parseUrl(Context context, String url) {
		try {
			return parseUrl(context, Uri.parse(url));
		} catch (Exception e) {
			return false;
		}
	}

	private static boolean parseUrl(final Context context, Uri uri) {
		try {
			SchemeAction schemeAction = SchemeHandlerFactory.createHandler(uri);
			if (schemeAction != null) {
				schemeAction.processUrl(context, uri);
				return true;
			}
			return false;
		} catch (Exception ignored) {
			return false;
		}
	}

	public static boolean isFairyScheme(Uri uri) {
		return uri != null && SCHEME.equals(uri.getScheme());
	}

	public static boolean isFairyScheme(String url) {
		return StringUtil.isNotEmpty(url) && isFairyScheme(Uri.parse(url));
	}
}
