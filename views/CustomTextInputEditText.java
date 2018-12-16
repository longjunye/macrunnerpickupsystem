package com.fairymo.macrunnerpickupsystem.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;

public class CustomTextInputEditText extends TextInputEditText {
	public CustomTextInputEditText(Context context) {
		super(context);
		applyCustomFont(context);
	}

	public CustomTextInputEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		applyCustomFont(context);
	}

	public CustomTextInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		applyCustomFont(context);
	}

	private void applyCustomFont(Context context) {
		Typeface customFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/Heiti-Regular.ttf");
		setTypeface(customFont);
	}
}
