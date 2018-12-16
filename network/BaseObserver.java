package com.fairymo.macrunnerpickupsystem.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;
import com.fairymo.macrunnerpickupsystem.CallingApplication;
import com.fairymo.macrunnerpickupsystem.constants.Constant;
import com.fairymo.macrunnerpickupsystem.entity.BaseEntity;
import com.fairymo.macrunnerpickupsystem.utils.StringUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public abstract class BaseObserver<T> implements Observer<Response<BaseEntity<T>>> {

	private static final String TAG = "BaseObserver";

	@Override
	public void onSubscribe(Disposable d) {

	}

	@Override
	public void onNext(Response<BaseEntity<T>> response) {
		if (response.code() != 200) {
			Toast.makeText(CallingApplication.getApp(), response.message(), Toast.LENGTH_SHORT).show();
		} else {
			T data;
			if ((data = response.body().getData()) != null) {
				onHandleSuccess(response.body().getCount(), data);
			} else {
				if (response.body().getMessage().equals(Constant.REQUEST_SUCCESS) && response.body().getCode() == 0) {
					return;
				}
				onHandleError(response.body().getMessage());
			}
		}

	}

	@Override
	public void onError(Throwable e) {
		if (StringUtil.isNotEmpty(e.getMessage())) {
			Toast.makeText(CallingApplication.getApp(), e.getMessage(), Toast.LENGTH_SHORT).show();
		}
		onHandleError("");
	}

	@Override
	public void onComplete() {
		Log.d(TAG, "onComplete");
	}

	abstract void onHandleSuccess(int total, @NonNull T data);

	private void onHandleError(@Nullable String msg) {
		if (StringUtil.isNotEmpty(msg)) {
			Toast.makeText(CallingApplication.getApp(), msg, Toast.LENGTH_SHORT).show();
		}
	}
}
