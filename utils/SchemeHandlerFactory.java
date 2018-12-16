package com.fairymo.macrunnerpickupsystem.utils;

import android.net.Uri;
import android.support.annotation.Nullable;

public class SchemeHandlerFactory {
	@Nullable
	static SchemeAction createHandler(final Uri uri) {
		if (uri == null) {
			return null;
		}

		if (!SchemeHandler.isFairyScheme(uri)) {
			return null;
		}

		final String host = uri.getHost();
		if (host == null) {
			return null;
		}

		switch (host) {
			//TODO
			default:
				return null;
		}
	}
}
