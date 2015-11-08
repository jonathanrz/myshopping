package br.com.jonathanzanella.myshopping.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.jonathanzanella.myshopping.R;
import br.com.jonathanzanella.myshopping.models.Product;
import butterknife.Bind;

/**
 * Created by jonathan on 08/11/15.
 * Copyright (c) 2015 Sparta Labs. All rights reserved.
 */
public class EditProductActivity extends BaseActivity {
	public static final String KEY_PRODUCT_ID = "KeyProductId";
	@Bind(R.id.act_edit_product_name)
	EditText editName;
	@Bind(R.id.act_edit_product_starts)
	EditText editStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_product);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
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
		Product product = new Product();
		product.setName(editName.getText().toString());
		product.setStars(Integer.parseInt(editStart.getText().toString()));
		product.save();

		Intent i = new Intent();
		i.putExtra(KEY_PRODUCT_ID, product.getId());
		setResult(RESULT_OK, i);
		finish();
	}
}
