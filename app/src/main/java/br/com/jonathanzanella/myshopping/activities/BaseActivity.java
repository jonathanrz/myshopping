package br.com.jonathanzanella.myshopping.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.jonathanzanella.myshopping.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jonathan on 08/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
public class BaseActivity extends AppCompatActivity {
	@Bind(R.id.toolbar)
	Toolbar toolbar;

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		ButterKnife.bind(this);

		setSupportActionBar(toolbar);

		storeBundle(savedInstanceState);
		storeBundle(getIntent().getExtras());
	}

	protected void storeBundle(Bundle extras) {
	}
}
