package com.fairymo.macrunnerpickupsystem;

import android.content.Context;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;
import com.fairymo.macrunnerpickupsystem.utils.JpushAliasOperatorHelper;

public class CallingJPushMessageReceiver extends JPushMessageReceiver {

	@Override
	public void onTagOperatorResult(Context context,JPushMessage jPushMessage) {
		JpushAliasOperatorHelper.getInstance().onTagOperatorResult(context,jPushMessage);
		super.onTagOperatorResult(context, jPushMessage);
	}
	@Override
	public void onCheckTagOperatorResult(Context context,JPushMessage jPushMessage){
		JpushAliasOperatorHelper.getInstance().onCheckTagOperatorResult(context,jPushMessage);
		super.onCheckTagOperatorResult(context, jPushMessage);
	}
	@Override
	public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
		JpushAliasOperatorHelper.getInstance().onAliasOperatorResult(context,jPushMessage);
		super.onAliasOperatorResult(context, jPushMessage);
	}

	@Override
	public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
		JpushAliasOperatorHelper.getInstance().onMobileNumberOperatorResult(context,jPushMessage);
		super.onMobileNumberOperatorResult(context, jPushMessage);
	}
}
