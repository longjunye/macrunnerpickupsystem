package com.fairymo.macrunnerpickupsystem.option;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fairymo.macrunnerpickupsystem.R;

public class PreparedOptionView extends AbstractOptionView<TextView> {

	public PreparedOptionView(@NonNull Context context, @NonNull ViewGroup parent) {
		super(context, parent);
	}

	@NonNull
	@Override
	protected TextView createView(@NonNull Context context, @NonNull ViewGroup parent) {
		TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.prepared_text_option, parent, false);
		Typeface customFont = Typeface.createFromAsset(context.getAssets(), "fonts/Heiti-Regular.ttf");
		textView.setTypeface(customFont);
		return textView;
	}

	@Override
	public void displayOption(@Nullable String option) {
		asView().setText(option);

	}
}
