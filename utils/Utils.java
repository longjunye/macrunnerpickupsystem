package com.fairymo.macrunnerpickupsystem.utils;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.fairymo.macrunnerpickupsystem.CallingApplication;
import com.fairymo.macrunnerpickupsystem.constants.Constant;
import okhttp3.RequestBody;
import okio.Buffer;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;

public class Utils {

	public static String getUserAgent() {
		return "Android" +
			"|" + Build.VERSION.RELEASE +
			"|" + Utils.getVersion() +
			"|" + Build.MODEL +
			"|" + Utils.getWindowWidth() + "*" + Utils.getWindowHeight() +
			"|" + Utils.getActiveNetworkType(CallingApplication.getApp());
	}

	/**
	 * * @return 获取版本号
	 */
	public static String getVersion() {
		PackageManager manager = CallingApplication.getApp().getPackageManager();
		PackageInfo info;
		try {
			info = manager.getPackageInfo(CallingApplication.getApp().getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}
		return info.versionName;
	}

	private static int getWindowWidth() {
		WindowManager wm = (WindowManager) (CallingApplication.getApp()
			.getSystemService(Context.WINDOW_SERVICE));
		if (wm != null) {
			DisplayMetrics dm = new DisplayMetrics();
			wm.getDefaultDisplay().getMetrics(dm);
			return dm.widthPixels;
		}
		return 0;
	}

	private static int getWindowHeight() {
		WindowManager wm = (WindowManager) (CallingApplication.getApp()
			.getSystemService(Context.WINDOW_SERVICE));
		if (wm != null) {
			DisplayMetrics dm = new DisplayMetrics();
			wm.getDefaultDisplay().getMetrics(dm);
			return dm.heightPixels;
		}
		return 0;
	}

	//获得状态栏/通知栏的高度
	public static int getStatusBarHeight(Context context) {
		Class<?> c;
		Object obj;
		Field field;
		int x, statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return statusBarHeight;
	}

	/**
	 * 获取当前应用的版本号
	 */
	public static String getVersionName() {
		PackageManager packageManager = CallingApplication.getApp().getPackageManager();
		PackageInfo packInfo;
		try {
			packInfo = packageManager.getPackageInfo(CallingApplication.getApp().getPackageName(), 0);
			return packInfo.versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return "1.0.0";
		}
	}

	/**
	 * 实现文本复制功能
	 *
	 * @param content 复制的文本
	 */
	public static void copy(String content) {
		// 得到剪贴板管理器
		ClipboardManager cmb = (ClipboardManager) CallingApplication.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
		cmb.setText(content.trim());
	}

	/**
	 * 使用浏览器打开链接
	 */
	public static void openLink(Context context, String content) {
		Uri issuesUrl = Uri.parse(content);
		Intent intent = new Intent(Intent.ACTION_VIEW, issuesUrl);
		context.startActivity(intent);
	}

	private static String getActiveNetworkType(@Nullable Context context) {
		String state = "unknown";
		Context app = context;
		if (app == null) {
			app = CallingApplication.getApp();
		}
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
			int type = networkInfo.getType();
			if (type == ConnectivityManager.TYPE_MOBILE_HIPRI
				|| type == ConnectivityManager.TYPE_MOBILE_MMS
				|| type == ConnectivityManager.TYPE_MOBILE_SUPL
				|| type == ConnectivityManager.TYPE_MOBILE_DUN
				|| type == ConnectivityManager.TYPE_MOBILE
				) {
				int networkInfoSubtype = networkInfo.getSubtype();
				switch (networkInfoSubtype) {
					case TelephonyManager.NETWORK_TYPE_UMTS:
					case TelephonyManager.NETWORK_TYPE_EVDO_0:
					case TelephonyManager.NETWORK_TYPE_EVDO_A:
					case TelephonyManager.NETWORK_TYPE_HSDPA:
					case TelephonyManager.NETWORK_TYPE_HSUPA:
					case TelephonyManager.NETWORK_TYPE_HSPA:
					case TelephonyManager.NETWORK_TYPE_EVDO_B:
					case TelephonyManager.NETWORK_TYPE_EHRPD:
					case TelephonyManager.NETWORK_TYPE_HSPAP:
						return "3g";
					case TelephonyManager.NETWORK_TYPE_GPRS:
					case TelephonyManager.NETWORK_TYPE_EDGE:
					case TelephonyManager.NETWORK_TYPE_CDMA:
					case TelephonyManager.NETWORK_TYPE_1xRTT:
					case TelephonyManager.NETWORK_TYPE_IDEN:
						return "2g";

					case TelephonyManager.NETWORK_TYPE_LTE:
						return "4g";
					default:
						return "unknown";
				}
			} else {
				state = networkInfo.getTypeName().toLowerCase();
			}
		} catch (Exception e) {
			//do nothing
		}
		return state;
	}

	public static boolean isSdCardExist() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	public static boolean isValidSmsCode(@NonNull String smsCode) {
		return Pattern.matches(Constant.REGEX_SMS_CODE, smsCode);
	}

	public static boolean isValidPhone(@NonNull String phone) {
		return Pattern.matches(Constant.REGEX_MOBILE, phone);
	}

	public static boolean nullSafeEquals(Object o1, Object o2) {
		if (o1 == o2) {
			return true;
		}
		if (o1 == null || o2 == null) {
			return false;
		}
		if (o1.equals(o2)) {
			return true;
		}
		if (o1.getClass().isArray() && o2.getClass().isArray()) {
			if (o1 instanceof Object[] && o2 instanceof Object[]) {
				return Arrays.equals((Object[]) o1, (Object[]) o2);
			}
			if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
				return Arrays.equals((boolean[]) o1, (boolean[]) o2);
			}
			if (o1 instanceof byte[] && o2 instanceof byte[]) {
				return Arrays.equals((byte[]) o1, (byte[]) o2);
			}
			if (o1 instanceof char[] && o2 instanceof char[]) {
				return Arrays.equals((char[]) o1, (char[]) o2);
			}
			if (o1 instanceof double[] && o2 instanceof double[]) {
				return Arrays.equals((double[]) o1, (double[]) o2);
			}
			if (o1 instanceof float[] && o2 instanceof float[]) {
				return Arrays.equals((float[]) o1, (float[]) o2);
			}
			if (o1 instanceof int[] && o2 instanceof int[]) {
				return Arrays.equals((int[]) o1, (int[]) o2);
			}
			if (o1 instanceof long[] && o2 instanceof long[]) {
				return Arrays.equals((long[]) o1, (long[]) o2);
			}
			if (o1 instanceof short[] && o2 instanceof short[]) {
				return Arrays.equals((short[]) o1, (short[]) o2);
			}
		}
		return false;
	}

	@Nullable
	public static String getCodeName(@NonNull Context mContext, @ArrayRes int arrayId, int codeId, int minusValue) {
		String[] clans;
		if (CollectionUtil.isEmpty(clans = mContext.getResources().getStringArray(arrayId))) {
			return StringUtil.EMPTY_STRING;
		}
		int clanIndex;
		clanIndex = codeId - minusValue > 0 ? codeId - minusValue : 0;
		if (clanIndex > clans.length) {
			return clans[0];
		} else {
			return clans[clanIndex];
		}
	}

	@NonNull
	public static String encryptByAlgorithm(@Nullable String source, @NonNull String algorithm) {
		String result = "";
		if (StringUtil.isNotEmpty(source)) {
			try {
				MessageDigest crypt = MessageDigest.getInstance(algorithm);
				crypt.reset();
				crypt.update(source.getBytes());
				result = byteToHex(crypt.digest());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Nullable
	public static byte[] encryptByHmacSha256(@NonNull String message, @NonNull String secret) {
		try {
			Mac sha256_HMAC = Mac.getInstance(Constant.ALGORITHM_HMAC_256);
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), Constant.ALGORITHM_HMAC_256);
			sha256_HMAC.init(secret_key);
			return sha256_HMAC.doFinal(message.getBytes());
		} catch (Exception e) {
			Log.i("CallingApplication", "HmacSHA256 error:" + e.getMessage());
		}
		return null;
	}

	@NonNull
	public static String getBase64(@Nullable byte[] str) {
		String result = "";
		if (str != null) {
			try {
				result = new String(Base64.encode(str, Base64.NO_WRAP), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String generateNonce() {
		Random rand = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= 32; i++) {
			int randNum = rand.nextInt(9) + 1;
			String num = randNum + "";
			sb = sb.append(num);
		}
		return String.valueOf(sb);
	}

	private static String byteToHex(final byte[] hash) {
		StringBuilder hs = new StringBuilder();
		String tempString = "";
		for (byte aHash : hash) {
			tempString = (Integer.toHexString(aHash & 0XFF));
			if (tempString.length() == 1) {
				hs.append("0").append(tempString);
			} else {
				hs.append(tempString);
			}
		}
		return hs.toString();
	}

	@Nullable
	public static String bodyToString(final RequestBody request) {
		try {
			final Buffer buffer = new Buffer();
			if (request != null)
				request.writeTo(buffer);
			else
				return "";
			return buffer.readUtf8();
		} catch (final IOException e) {
			return null;
		}
	}

	public static void showToast(@NonNull String message, @NonNull Context context) {
		if (StringUtil.isEmpty(message)) {
			return;
		}
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	public static void keepScreenLongLight(@NonNull Activity activity) {
		Window window = activity.getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

	}
}


