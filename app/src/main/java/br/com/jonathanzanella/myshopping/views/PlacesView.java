package br.com.jonathanzanella.myshopping.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import br.com.jonathanzanella.myshopping.R;
import br.com.jonathanzanella.myshopping.activities.EditPlaceActivity;
import br.com.jonathanzanella.myshopping.adapter.PlacesAdapter;
import br.com.jonathanzanella.myshopping.models.Place;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
public class PlacesView extends BaseView {
	private static final int REQUEST_ADD_PLACE = 1003;
	private PlacesAdapter adapter;

	@Bind(R.id.view_places_list)
	RecyclerView places;

	public PlacesView(Context context) {
		super(context);
	}

	public PlacesView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PlacesView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void init() {
		inflate(getContext(), R.layout.view_places, this);
		ButterKnife.bind(this);

		adapter = new PlacesAdapter();
		adapter.loadData();

		places.setAdapter(adapter);
		places.setLayoutManager(new LinearLayoutManager(getContext()));
		places.setItemAnimator(new DefaultItemAnimator());
	}

	@OnClick(R.id.view_places_fab)
	void onFab() {
		Context ctx = getContext();
		Intent i = new Intent(getContext(), EditPlaceActivity.class);
		if(ctx instanceof Activity) {
			((Activity) ctx).startActivityForResult(i, REQUEST_ADD_PLACE);
		} else {
			ctx.startActivity(i);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
			case REQUEST_ADD_PLACE:
				if(resultCode == Activity.RESULT_OK) {
					Place p = Place.find(data.getLongExtra(EditPlaceActivity.KEY_PLACE_ID, 0L));
					if(p != null)
						adapter.addPlace(p);
				}
				break;
		}
	}
}