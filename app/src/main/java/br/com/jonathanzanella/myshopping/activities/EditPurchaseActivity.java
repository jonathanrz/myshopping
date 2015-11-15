package br.com.jonathanzanella.myshopping.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.com.jonathanzanella.myshopping.R;
import br.com.jonathanzanella.myshopping.adapter.SelectablePlacesAdapter;
import br.com.jonathanzanella.myshopping.models.Place;
import br.com.jonathanzanella.myshopping.models.Purchase;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jonathan on 08/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
public class EditPurchaseActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {
	public static final String KEY_PURCHASE_ID = "KeyPurchaseId";
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
	@Bind(R.id.act_edit_purchase_date)
	EditText editDate;
	@Bind(R.id.act_edit_purchase_places_list)
	RecyclerView listPlaces;

	private Calendar selectedDate;
	private SelectablePlacesAdapter placesAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_purchase);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		selectedDate = Calendar.getInstance();
		onDateUpdated();

		placesAdapter = new SelectablePlacesAdapter();
		placesAdapter.loadData();
		listPlaces.setLayoutManager(new LinearLayoutManager(this));
		listPlaces.setAdapter(placesAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.save, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_save:
				save();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	@OnClick(R.id.act_edit_purchase_date)
	void onDate() {
		new DatePickerDialog(this, this, selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH),
				selectedDate.get(Calendar.DAY_OF_MONTH)).show();
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		selectedDate.set(Calendar.YEAR, year);
		selectedDate.set(Calendar.MONTH, monthOfYear);
		selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

		onDateUpdated();
	}

	private void onDateUpdated() {
		editDate.setText(sdf.format(selectedDate.getTime()));
	}

	private void save() {
		Purchase purchase = new Purchase();
		purchase.setDate(selectedDate.getTime());
		Place p = placesAdapter.getSelectedPlace();
		if(p != null)
			purchase.setPlaceId(p.getId());
		purchase.save();

		Intent i = new Intent();
		i.putExtra(KEY_PURCHASE_ID, purchase.getId());
		setResult(RESULT_OK, i);
		finish();
	}
}
