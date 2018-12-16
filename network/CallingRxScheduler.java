package com.fairymo.macrunnerpickupsystem.network;

import android.widget.Toast;
import com.fairymo.macrunnerpickupsystem.CallingApplication;
import com.fairymo.macrunnerpickupsystem.R;
import com.fairymo.macrunnerpickupsystem.utils.NetUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CallingRxScheduler {
	public static <T> ObservableTransformer<T, T> compose() {
		return new ObservableTransformer<T, T>() {
			@Override
			public ObservableSource<T> apply(Observable<T> observable) {
				return observable
					.subscribeOn(Schedulers.io())
					.doOnSubscribe(new Consumer<Disposable>() {
						@Override
						public void accept(Disposable disposable) throws Exception {
							if (!NetUtil.isNetworkAvailable()) {
								Toast.makeText(CallingApplication.getApp(), R.string.internet_error, Toast.LENGTH_SHORT).show();
							}
						}
					})
					.observeOn(AndroidSchedulers.mainThread());
			}
		};
	}
}
