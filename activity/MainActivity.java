package com.fairymo.macrunnerpickupsystem.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import com.fairymo.macrunnerpickupsystem.CallingApplication;
import com.fairymo.macrunnerpickupsystem.R;
import com.fairymo.macrunnerpickupsystem.constants.Constant;
import com.fairymo.macrunnerpickupsystem.entity.BaseEntity;
import com.fairymo.macrunnerpickupsystem.entity.JpushMessage;
import com.fairymo.macrunnerpickupsystem.entity.OptionStatus;
import com.fairymo.macrunnerpickupsystem.network.CallingRequestFactory;
import com.fairymo.macrunnerpickupsystem.network.CallingRxScheduler;
import com.fairymo.macrunnerpickupsystem.option.OptionListView;
import com.fairymo.macrunnerpickupsystem.option.OptionStatusConstants;
import com.fairymo.macrunnerpickupsystem.utils.CollectionUtil;
import com.fairymo.macrunnerpickupsystem.utils.LocalBroadcastManager;
import com.fairymo.macrunnerpickupsystem.utils.SharedPreferencesUtil;
import com.fairymo.macrunnerpickupsystem.utils.Utils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainActivity extends BaseActivity {
	private String[] options = new String[] { OptionStatusConstants.PREPARED, OptionStatusConstants.PREPARING };
	@BindView(R.id.container)
	LinearLayout container;
	Map<String, OptionListView> optionViews = new HashMap<>();
	public static boolean isForeground = false;
	private MessageReceiver mMessageReceiver;
	public static final String MESSAGE_RECEIVED_ACTION = "com.fairymo.macrunnerpickupsystem.MESSAGE_RECEIVED_ACTION";
	public static final String KEY_TITLE = "title";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_EXTRAS = "extras";

	@SuppressLint("CheckResult")
	@Override
	protected void init() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		for (String option : options) {
			OptionListView optionListView = new OptionListView(this, option);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
			layoutParams.gravity = Gravity.CENTER;
			optionListView.setLayoutParams(layoutParams);
			container.addView(optionListView);
			optionViews.put(option, optionListView);
		}
		requestOptions();
		registerMessageReceiver();
		loop();
		Utils.keepScreenLongLight(this);
	}

	protected void onResume() {
		super.onResume();
		isForeground = true;
		JPushInterface.onResume(this);
	}

	protected void onPause() {
		super.onPause();
		isForeground = false;
		JPushInterface.onPause(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
	}

	public void registerMessageReceiver() {
		mMessageReceiver = new MessageReceiver();
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(MESSAGE_RECEIVED_ACTION);
		LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
	}

	public class MessageReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			try {
				if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
					final JpushMessage message = (JpushMessage) intent.getSerializableExtra(KEY_MESSAGE);
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							for (String status : optionViews.keySet()) {
								OptionListView optionListView = optionViews.get(status);
								optionListView.update(message.getRunnerState(), message.getRunnerPickupCodes(), false);
							}
						}
					});

				}
			} catch (Exception ignored) {
			}
		}
	}

	@SuppressLint("CheckResult")
	private void loop() {
		Observable.timer(30000, TimeUnit.MILLISECONDS)
			.subscribeOn(Schedulers.io())
			.repeat()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Consumer<Long>() {
				@Override
				public void accept(Long aLong) {
					if (CollectionUtil.isEmpty(optionViews)) {
						return;
					}
					requestOptions();
				}
			});
	}

	public void requestOptions() {
		for (final String optionStatus : options) {
			Observable<Response<BaseEntity<OptionStatus>>> observable = CallingRequestFactory.getInstance().getOptions(
				SharedPreferencesUtil.getString(CallingApplication.getApp(),
					Constant.SHOPPING_NO, Constant.DEFAULT_SHOPPING_NO), SharedPreferencesUtil.getString(CallingApplication.getApp(),
					Constant.BRAND_NO, Constant.DEFAULT_BRAND_NO), optionStatus);
			observable.compose(CallingRxScheduler.<Response<BaseEntity<OptionStatus>>>compose()).subscribe(
				new Observer<Response<BaseEntity<OptionStatus>>>() {
					@Override
					public void onSubscribe(Disposable d) {
					}

					@Override
					public void onNext(Response<BaseEntity<OptionStatus>> response) {
						if (response == null || response.body() == null || response.body().getData() == null || CollectionUtil.isEmpty(
							response.body().getData().getPickupCodes())) {
							return;
						}
						for (String status : optionViews.keySet()) {
							OptionListView optionListView = optionViews.get(status);
							optionListView.update(optionStatus, response.body().getData().getPickupCodes(), true);
						}
					}

					@Override
					public void onError(Throwable e) {
						Log.i("ylj", e.getLocalizedMessage());
					}

					@Override
					public void onComplete() {
						Log.i("ylj", "onComplete");
					}
				});
		}

	}
}
