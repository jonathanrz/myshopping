package br.com.jonathanzanella.myshopping.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.jonathanzanella.myshopping.R;
import br.com.jonathanzanella.myshopping.models.Place;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015 Sparta Labs. All rights reserved.
 */
public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {
	protected List<Place> places;

	public static class ViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.row_place_name)
		TextView name;

		public ViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);
		}

		public void setData(Place place) {
			name.setText(place.getName());
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_place, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.setData(places.get(position));
	}

	@Override
	public int getItemCount() {
		return places != null ? places.size() : 0;
	}

	public void loadData() {
		places = Place.all();
	}

	public void addPlace(@NonNull Place place) {
		places.add(place);
		notifyItemInserted(places.size() - 1);
	}
}
