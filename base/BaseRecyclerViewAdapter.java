package com.fairymo.macrunnerpickupsystem.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.fairymo.macrunnerpickupsystem.R;
import com.fairymo.macrunnerpickupsystem.utils.DensityUtil;
import com.fairymo.macrunnerpickupsystem.views.AnimateFirstDisplayListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

	protected ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	protected ViewClickListener clickListener;

	protected DisplayImageOptions options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.loading_error)
		.showImageForEmptyUri(R.mipmap.ic_launcher)
		.showImageOnFail(R.drawable.loading_error)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(DensityUtil.dip2px(4)))
		.build();

	@NonNull
	protected List<T> data = new ArrayList<>();

	@Override
	public int getItemCount() {
		return data.size();
	}

	public void addAll(List<T> data) {
		this.data.addAll(data);
	}

	public void add(T object) {
		data.add(object);
	}

	public void add(int position, T object) {
		data.add(position, object);
	}

	public void clear() {
		data.clear();
	}

	public void remove(T object) {
		data.remove(object);
	}

	public void remove(int position) {
		data.remove(position);
	}

	public void removeAll(List<T> data) {
		this.data.retainAll(data);
	}

	@NonNull
	public List<T> getData() {
		return data;
	}

	public void setClickListener(@Nullable ViewClickListener clickListener) {
		this.clickListener = clickListener;
	}

	public interface ViewClickListener {
		void onClick(@NonNull View view, int position);
	}
}
