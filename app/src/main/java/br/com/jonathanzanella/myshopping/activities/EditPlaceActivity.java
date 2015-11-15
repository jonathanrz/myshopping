package br.com.jonathanzanella.myshopping.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.jonathanzanella.myshopping.R;
import br.com.jonathanzanella.myshopping.models.Place;
import butterknife.Bind;

/**
 * Created by jonathan on 08/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
public class EditPlaceActivity extends BaseActivity {
	public static final String KEY_PLACE_ID = "KeyPlaceId";
	@Bind(R.id.act_edit_place_name)
	EditText editName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_place);
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

	private void save() {
		Place place = new Place();
		place.setName(editName.getText().toString());
		place.save();

		Intent i = new Intent();
		i.putExtra(KEY_PLACE_ID, place.getId());
		setResult(RESULT_OK, i);
		finish();
	}
}
