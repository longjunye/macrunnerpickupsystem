package com.fairymo.macrunnerpickupsystem.option;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class OptionStatusConstants {
	@Retention(SOURCE)
	@StringDef({
		PREPARING, PREPARED, OTHERS
	})
	public @interface SourceType {
	}

	public static final String PREPARING = "PREPARING";
	public static final String PREPARED = "PREPARED";
	public static final String OTHERS = "OTHERS";
}
