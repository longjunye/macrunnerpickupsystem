package com.fairymo.macrunnerpickupsystem.entity;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

public class BaseEntity<E> {
	@SerializedName("code")
	private int code;
	@SerializedName("message")
	private String message;
	@SerializedName("data")
	private E data;
	@SerializedName("count")
	private int count;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@NonNull
	@Override
	public String toString() {
		return "BaseEntity{" +
			"code=" + code +
			", message='" + message + '\'' +
			", data=" + data +
			", count=" + count +
			'}';
	}
}
