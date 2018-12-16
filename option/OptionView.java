package com.fairymo.macrunnerpickupsystem.option;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public interface OptionView<T extends View> {
	void displayOption(@Nullable String content);

	@NonNull
	T asView();
}
