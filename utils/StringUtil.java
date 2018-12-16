package com.fairymo.macrunnerpickupsystem.utils;

import android.text.SpannableString;

public class StringUtil {
	public static final String EMPTY_STRING = "";
	public static String checkEmpty(String str) {
		return checkEmpty(str, EMPTY_STRING);
	}

	public static String checkEmpty(String str, String defaultString) {
		if (str == null || "".equals(str) || "null".equals(str)) {
			return defaultString;
		}
		return str.trim();
	}

	public static boolean isEmpty(String str) {
		return (str == null) || (str.trim().length() == 0);
	}

	public static boolean isEmpty(SpannableString str) {
		return ((str == null) || isEmpty(str.toString()));
	}

	public static boolean isNotEmpty(SpannableString str) {
		return !isEmpty(str);
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isEmpty(StringBuilder sb) {
		return (sb == null || isEmpty(sb.toString()));
	}

	public static boolean isNotEmpty(StringBuilder sb) {
		return !isEmpty(sb);
	}

	public static boolean isEmpty(CharSequence str) {
		return ((str == null) || str.toString().trim().length() == 0);
	}

	public static boolean isNotEmpty(CharSequence str) {
		return !isEmpty(str);
	}
}
