package com.fairymo.macrunnerpickupsystem.option;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.fairymo.macrunnerpickupsystem.utils.CollectionUtil;

import java.util.List;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHolder> {
	private String optionStatus;
	@NonNull
	private List<String> data;

	public OptionAdapter(String optionStatus, @NonNull List<String> data) {
		this.optionStatus = optionStatus;
		this.data = data;
	}

	public void setData(@NonNull List<String> data) {
		this.data = data;
	}

	@NonNull
	@Override
	public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		AbstractOptionView v;
		if (OptionStatusConstants.PREPARED.equals(optionStatus)) {
			v = new PreparedOptionView(parent.getContext(), parent);
		} else {
			v = new PreparingOptionView(parent.getContext(), parent);
		}
		return new OptionViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {
		if (CollectionUtil.isEmpty(data) || data.size() < position) {
			return;
		}
		holder.view.displayOption(data.get(position));
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	public class OptionViewHolder extends RecyclerView.ViewHolder {
		@NonNull
		AbstractOptionView view;

		OptionViewHolder(@NonNull AbstractOptionView abstractOptionView) {
			super(abstractOptionView.asView());
			this.view = abstractOptionView;
		}
	}
}
