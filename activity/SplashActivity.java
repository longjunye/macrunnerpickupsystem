package com.fairymo.macrunnerpickupsystem.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import butterknife.ButterKnife;
import com.fairymo.macrunnerpickupsystem.R;
import com.fairymo.macrunnerpickupsystem.constants.Constant;
import com.fairymo.macrunnerpickupsystem.utils.SharedPreferencesUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends BaseActivity {

	@SuppressLint("CheckResult")
	@Override
	protected void init() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);
		ButterKnife.bind(this);
		Observable.timer(2000, TimeUnit.MILLISECONDS)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Consumer<Long>() {
				@Override
				public void accept(Long aLong) {
					boolean isSet = SharedPreferencesUtil.getBoolean(SplashActivity.this, Constant.SHOPPING_ID_SET, false);
					Intent intent;
					intent = new Intent(SplashActivity.this, isSet ? MainActivity.class : SettingActivity.class);
//					intent = new Intent(SplashActivity.this, SettingActivity.class);
					startActivity(intent);
					finish();
				}
			});
	}
}
