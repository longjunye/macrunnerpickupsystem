package com.fairymo.macrunnerpickupsystem;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.fairymo.macrunnerpickupsystem.activity.SplashActivity;

public class BootReceiver extends BroadcastReceiver {
	@SuppressLint("UnsafeProtectedBroadcastReceiver")
	@Override
	public void onReceive(Context context, Intent intent) {
		Intent it=new Intent(context,SplashActivity.class);
		it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(it);
	}
}
