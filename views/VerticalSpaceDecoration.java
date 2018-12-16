package com.fairymo.macrunnerpickupsystem.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.fairymo.macrunnerpickupsystem.utils.DensityUtil;

public class VerticalSpaceDecoration extends RecyclerView.ItemDecoration {
	private static final int[] ATTRS = new int[] { android.R.attr.listDivider };
	private Drawable mDivider;
	private static final float RECYCLER_VIEW_VERTICAL_MARGIN = 4.0f;

	public VerticalSpaceDecoration(@NonNull Context context) {
		final TypedArray a = context.obtainStyledAttributes(ATTRS);
		mDivider = a.getDrawable(0);
		a.recycle();
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		drawDivider(c, parent);
	}

	private void drawDivider(Canvas c, RecyclerView parent) {
		final int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			final View child = parent.getChildAt(i);
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
				.getLayoutParams();
			final int top = child.getTop() - params.topMargin;
			final int bottom = child.getBottom() + params.bottomMargin;
			final int left = child.getRight() + params.rightMargin;
			final int right = left + mDivider.getIntrinsicWidth();

			mDivider.setBounds(left, top, right, bottom);
			mDivider.draw(c);
		}
	}

	/**
	 * Retrieve any offsets for the given item. Each field of <code>outRect</code> specifies
	 * the number of pixels that the item content should be inset by, similar to padding or margin.
	 * The default implementation sets the bounds of outRect to 0 and returns.
	 * <p>
	 * <p>
	 * If this ItemDecoration does not affect the positioning of item views, it should set
	 * all four fields of <code>outRect</code> (left, top, right, bottom) to zero
	 * before returning.
	 * <p>
	 * <p>
	 * If you need to access Adapter for additional data, you can call
	 * {@link RecyclerView#getChildAdapterPosition(View)} to get the adapter position of the
	 * View.
	 *
	 * @param outRect Rect to receive the output.
	 * @param view    The child content to decorate
	 * @param parent  RecyclerView this ItemDecoration is decorating
	 * @param state   The current state of RecyclerView.
	 */
	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		final int position = parent.getChildAdapterPosition(view);
		if (position == RecyclerView.NO_POSITION) {
			return;
		}
		int verticalMargin = DensityUtil.dip2px(RECYCLER_VIEW_VERTICAL_MARGIN);
		outRect.top = verticalMargin;
		outRect.bottom = verticalMargin;
	}
}
