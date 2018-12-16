package com.fairymo.macrunnerpickupsystem.option;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.fairymo.macrunnerpickupsystem.CallingApplication;
import com.fairymo.macrunnerpickupsystem.R;
import com.fairymo.macrunnerpickupsystem.constants.Constant;
import com.fairymo.macrunnerpickupsystem.entity.BaseEntity;
import com.fairymo.macrunnerpickupsystem.entity.OptionStatus;
import com.fairymo.macrunnerpickupsystem.network.CallingRequestFactory;
import com.fairymo.macrunnerpickupsystem.network.CallingRxScheduler;
import com.fairymo.macrunnerpickupsystem.utils.CollectionUtil;
import com.fairymo.macrunnerpickupsystem.utils.SharedPreferencesUtil;
import com.fairymo.macrunnerpickupsystem.views.GridSpacingItemDecoration;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class OptionListView extends LinearLayout {

	@BindView(R.id.title)
	TextView title;
	@BindView(R.id.title_en)
	TextView titleEn;
	@BindView(R.id.option_list)
	RecyclerView optionList;
	private String optionStatus;
	private List<String> currentData = new ArrayList<>();
	@Nullable
	private OptionAdapter optionAdapter;

	public OptionListView(@NonNull Context context) {
		super(context);
		init(context);
	}

	public OptionListView(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public OptionListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public OptionListView(@NonNull Context context, @NonNull String optionStatus) {
		super(context);
		this.optionStatus = optionStatus;
		init(context);
	}

	private void init(@NonNull Context context) {
		ButterKnife.bind(this, inflate(getContext(), R.layout.option_list, this));
		optionList.setLayoutManager(new GridLayoutManager(getContext(), 3));
		optionList.addItemDecoration(new GridSpacingItemDecoration(3, 4, false));
		setTextType();
		setTitles();
		requestOptions();
	}

	private void setTextType() {
		Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Heiti-Regular.ttf");
		title.setTypeface(typeface);
		titleEn.setTypeface(typeface);
	}

	private void setTitles() {
		if (optionStatus == null) {
			return;
		}

		if (OptionStatusConstants.PREPARED.equals(optionStatus)) {
			title.setText(R.string.please_pick_up);
			titleEn.setText(R.string.please_pick_up_en);
		} else if (OptionStatusConstants.PREPARING.equals(optionStatus)) {
			title.setText(R.string.please_wait_up);
			titleEn.setText(R.string.please_wait_up_en);
		}
	}

	//request option status, display it.
	public void requestOptions() {
		Observable<Response<BaseEntity<OptionStatus>>> observable = CallingRequestFactory.getInstance().getOptions(
			SharedPreferencesUtil.getString(CallingApplication.getApp(),
				Constant.SHOPPING_NO, Constant.DEFAULT_SHOPPING_NO), SharedPreferencesUtil.getString(CallingApplication.getApp(),
				Constant.SHOPPING_NO, Constant.DEFAULT_BRAND_NO), optionStatus);
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
					update(optionStatus, response.body().getData().getPickupCodes());
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

	public void update(@Nullable String status, @Nullable List<String> pickUpCodes) {
		if (CollectionUtil.isEmpty(pickUpCodes)) {
			return;
		}
		for (String code : pickUpCodes) {
			if (OptionStatusConstants.PREPARED.equals(optionStatus)) {
				if (!currentData.contains(code) && OptionStatusConstants.PREPARED.equals(status)) {
					currentData.add(code);
				}
			} else if (OptionStatusConstants.PREPARING.equals(optionStatus)) {
				if (!currentData.contains(code) && OptionStatusConstants.PREPARING.equals(status)) {
					currentData.add(code);
				}
				if (currentData.contains(code) && OptionStatusConstants.PREPARED.equals(status)) {
					currentData.remove(code);
				}
			}
		}
		if (optionAdapter == null) {
			optionAdapter = new OptionAdapter(optionStatus, currentData);
			optionList.setAdapter(optionAdapter);
		} else {
			optionAdapter.setData(currentData);
			optionAdapter.notifyDataSetChanged();
		}
	}
}
