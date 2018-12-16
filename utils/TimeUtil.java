package com.fairymo.macrunnerpickupsystem.utils;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {

	private static String NORMAL_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static String NORMAL_TIME_FORMAT_REQUEST = "yyyy-MM-dd HH:mm";
	private static String BANK_CARD_TIME_FORMAT = "yyyyMMddHHmmss";

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(NORMAL_TIME_FORMAT, Locale.ENGLISH);
	private static SimpleDateFormat requestDateFormat = new SimpleDateFormat(NORMAL_TIME_FORMAT_REQUEST, Locale.ENGLISH);
	private static SimpleDateFormat bankcardDateFormat = new SimpleDateFormat(BANK_CARD_TIME_FORMAT, Locale.ENGLISH);

	@NonNull
	public static String translateTime(String time) {
		Date nowTime = new Date();
		long currentMilliseconds = nowTime.getTime();
		Date date;
		try {
			date = dateFormat.parse(time);
		} catch (Exception e) {
			e.printStackTrace();
			return time;
		}

		long timeDifferent = currentMilliseconds - date.getTime();
		if (timeDifferent < 5 * 60 * 1000) {
			return "刚刚";
		}
		if (timeDifferent < 60 * 60 * 1000) {
			long longMinute = timeDifferent / 60000;
			int minute = (int) (longMinute % 100);
			return minute + "分钟之前";
		}
		long l = 24 * 60 * 60 * 1000;
		if (timeDifferent < l) {
			long longHour = timeDifferent / 3600000;
			int hour = (int) (longHour % 100);
			return hour + "小时之前";
		}
		long week = 7 * 24 * 60 * 60 * 1000;
		if (timeDifferent < week) {
			long longDay = timeDifferent / (24 * 60 * 60 * 1000);
			int day = (int) (longDay % 100);
			return day + "天之前";
		}
		if (timeDifferent >= week) {
			return time.substring(0, 10);
		}
		return time;
	}

	@NonNull
	public static String convertTime(String time) {
		Date date;
		try {
			date = dateFormat.parse(time);
		} catch (Exception e) {
			e.printStackTrace();
			return time;
		}
		return  (1900 + date.getYear()) + "-"
			+ (date.getMonth() + 1)
			+ "-" + date.getDate()
			+ " " + date.getHours()
			+ ":" + (date.getMinutes() < 10 ? ("0" + date.getMinutes()) : date.getMinutes());
	}

	@NonNull
	public static String getFormattedTime() {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		Date date = calendar.getTime();
		return requestDateFormat.format(date);
	}

	@NonNull
	public static String getBankCardTimeFormattedTime() {
		Calendar calendar = Calendar.getInstance(Locale.getDefault());
		Date date = calendar.getTime();
		return bankcardDateFormat.format(date);
	}
}
