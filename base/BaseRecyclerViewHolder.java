package com.fairymo.macrunnerpickupsystem.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder {
	public BaseRecyclerViewHolder(View itemView) {
		super(itemView);
	}

	public abstract void onBindViewHolder(T t, int position);
}
