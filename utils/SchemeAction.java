package com.fairymo.macrunnerpickupsystem.utils;

import android.content.Context;
import android.net.Uri;

/**
 *
 */
public abstract class SchemeAction {
	abstract void getParameters(final Uri uri);

	abstract void handleUrl(final Context context);

	void processUrl(final Context context, final Uri uri) {
		getParameters(uri);
		handleUrl(context);
	}

}
