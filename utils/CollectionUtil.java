package com.fairymo.macrunnerpickupsystem.utils;

import java.util.Collection;
import java.util.Map;

public class CollectionUtil {
	public static final int INVALID_INDEX = -1;

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Collection collection) {
		return (collection == null || collection.isEmpty());
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Collection collection) {
		return !isEmpty(collection);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Map map) {
		return (map == null || map.isEmpty());
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Map map) {
		return !isEmpty(map);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isAnyEmpty(Collection... varCollection) {
		for (Collection collection : varCollection) {
			if (isEmpty(collection)) {
				return true;
			}
		}

		return false;
	}

	public static int getSize(Collection collection) {
		if (isEmpty(collection)) {
			return 0;
		} else {
			return collection.size();
		}
	}

	public static boolean isLastPosition(Collection collection, int position) {
		return (position >= 0 && (position == CollectionUtil.getSize(collection) - 1));
	}

	public static int getLastPosition(Collection collection) {
		int last = getSize(collection) - 1;
		return isLastPosition(collection, last) ? last : INVALID_INDEX;
	}

	public static boolean contains(Collection collection, Object obj) {
		if (isEmpty(collection)) {
			return false;
		} else {
			return collection.contains(obj);
		}
	}

	public static boolean isEmpty(Object[] array) {
		return array == null || array.length < 1;
	}

	public static boolean isNotEmpty(Object[] array) {
		return !isEmpty(array);
	}

	public static int getSize(Object[] array) {
		if (isEmpty(array)) {
			return 0;
		} else {
			return array.length;
		}
	}

	public static boolean isSafeIndex(Object[] array, int index) {
		if (isEmpty(array)) {
			return false;
		}

		return index >= 0 && getSize(array) > index;
	}

	public static boolean isEmpty(int[] array) {
		return array == null || array.length < 1;
	}

	public static boolean isNotEmpty(int[] array) {
		return !isEmpty(array);
	}

	public static int getSize(int[] array) {
		if (isEmpty(array)) {
			return 0;
		} else {
			return array.length;
		}
	}

	public static boolean isSafeIndex(Collection collection, int index) {
		if (isEmpty(collection)) {
			return false;
		}

		return index >= 0 && getSize(collection) > index;
	}

	public static boolean isOutOfIndex(Collection collection, int index) {
		return !isSafeIndex(collection, index);
	}

	public static int getSafeIndex(Collection collection, int index) {
		return getSafeIndex(collection, index, INVALID_INDEX);
	}

	public static int getSafeIndex(Collection collection, int index, int notSafeIndex) {
		return isSafeIndex(collection, index) ? index : notSafeIndex;
	}
}
