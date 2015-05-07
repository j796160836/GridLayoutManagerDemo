package com.johnnydemo.gridlayoutmanagerdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by johnnysung on 15/05/07.
 */
public class ElementsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	public class ElementsType {
		public static final int VIEW_HEADER = 0;
		public static final int VIEW_NORMAL = 1;
	}

	private int[] datasetSizes;

	public class HeaderViewHolder extends RecyclerView.ViewHolder {
		public TextView headerView;

		public HeaderViewHolder(TextView v) {
			super(v);
			headerView = v;
		}
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		public TextView textView;

		public ViewHolder(TextView v) {
			super(v);
			textView = v;
		}
	}

	public ElementsAdapter(int[] sizes) {
		this.datasetSizes = sizes;
	}

	public void setDatasetSizes(int[] datasetSizes) {
		this.datasetSizes = datasetSizes;
	}

	@Override
	public int getItemViewType(int pos) {
		int[] n = indexInSection(pos);
		return (n[1] == -1) ? ElementsType.VIEW_HEADER : ElementsType.VIEW_NORMAL;
	}

	@Override
	public int getItemCount() {
		int total = 0;
		for (int i = 0; i < datasetSizes.length; i++) {
			total += datasetSizes[i];
		}
		return total + datasetSizes.length;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		TextView textView;
		if (viewType == ElementsType.VIEW_HEADER) {
			textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_header, parent, false);
			return new HeaderViewHolder(textView);
		} else {
			textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_element, parent, false);
			return new ViewHolder(textView);
		}
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int pos) {

		int[] n = indexInSection(pos);

		if (viewHolder instanceof HeaderViewHolder) {
			HeaderViewHolder headerHolder = (HeaderViewHolder) viewHolder;
			headerHolder.headerView.setText("This is the header " + n[0]);

		} else if (viewHolder instanceof ViewHolder) {
			ViewHolder holder = (ViewHolder) viewHolder;
			holder.textView.setText("Sec " + n[0] + " Pos " + n[1]);
		}
	}

	private int[] indexInSection(int pos) {
		int num = 0;
		for (int i = 0; i < datasetSizes.length; i++) {
			if (pos - num == 0) {
				return new int[]{i, -1};
			} else if (pos - num > 0 && pos < num + datasetSizes[i] + 1) {
				return new int[]{i, pos - num - 1};
			}
			num += datasetSizes[i] + 1;
		}
		return new int[]{-1, -1};
	}
}
