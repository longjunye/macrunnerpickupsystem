package com.fairymo.macrunnerpickupsystem.option;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

public abstract class AbstractOptionView<T extends View> implements OptionView<T> {

	@NonNull
	protected Context context;
	@NonNull
	protected T optionView;
	@Nullable
	protected String option;

	public AbstractOptionView(@NonNull Context context, @NonNull ViewGroup parent) {
		this.context = context;
		optionView = createView(context, parent);
	}

	@NonNull
	protected abstract T createView(@NonNull Context context, @NonNull ViewGroup parent);

	@Override
	public void displayOption(@Nullable String option) {
		//
	}

	@NonNull
	@Override
	public T asView() {
		return optionView;
	}
}
