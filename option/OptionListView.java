package com.fairymo.macrunnerpickupsystem.option;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.fairymo.macrunnerpickupsystem.R;
import com.fairymo.macrunnerpickupsystem.utils.CollectionUtil;
import com.fairymo.macrunnerpickupsystem.views.GridSpacingItemDecoration;

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
	}

	private void setTextType() {
		Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Heiti-Regular.ttf");
		title.setTypeface(typeface);
		Typeface enTypeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/Helvetica.ttf");
		titleEn.setTypeface(enTypeFace);
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

	public void update(@Nullable String status, @Nullable List<String> pickUpCodes, boolean isFromServer) {
		if (CollectionUtil.isEmpty(pickUpCodes)) {
			return;
		}
		if (isFromServer) {
			if (optionStatus.equals(status)) {
				currentData = pickUpCodes;
			}
		} else {
			for (String code : pickUpCodes) {
				if (OptionStatusConstants.PREPARED.equals(optionStatus)) {
					if (OptionStatusConstants.PREPARED.equals(status) && !currentData.contains(code)) {
						currentData.add(code);
					} else if(OptionStatusConstants.DONE.equals(status) && currentData.contains(code)) {
						currentData.remove(code);
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
