package br.com.jonathanzanella.myshopping.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import br.com.jonathanzanella.myshopping.R;
import br.com.jonathanzanella.myshopping.models.Place;
import butterknife.ButterKnife;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
public class SelectablePlacesAdapter extends PlacesAdapter {
	private int selectedPosition = -1;

	public static class ViewHolder extends PlacesAdapter.ViewHolder implements View.OnClickListener {
		SelectablePlacesAdapter adapter;

		public ViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);
			itemView.setOnClickListener(this);
		}

		public void makeSelected(boolean b) {
			int color = itemView.getResources().getColor(b ? R.color.colorSelected : android.R.color.white);
			itemView.setBackgroundColor(color);
		}

		@Override
		public void onClick(View v) {
			adapter.selectedPosition = getAdapterPosition();
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public PlacesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new ViewHolder(super.onCreateViewHolder(parent, viewType).itemView);
	}

	@Override
	public void onBindViewHolder(PlacesAdapter.ViewHolder holder, int position) {
		ViewHolder vh = (ViewHolder) holder;
		vh.adapter = this;
		super.onBindViewHolder(holder, position);
		vh.makeSelected(position == selectedPosition);
	}

	public @Nullable Place getSelectedPlace() {
		if(selectedPosition < 0 || selectedPosition >= getItemCount())
			return null;

		return places.get(selectedPosition);
	}
}