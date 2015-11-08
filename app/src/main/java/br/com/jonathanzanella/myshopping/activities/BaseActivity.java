package br.com.jonathanzanella.myshopping.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by jonathan on 08/11/15.
 * Copyright (c) 2015 Sparta Labs. All rights reserved.
 */
public class BaseActivity extends AppCompatActivity {
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		ButterKnife.bind(this);

		storeBundle(savedInstanceState);
		storeBundle(getIntent().getExtras());
	}

	protected void storeBundle(Bundle extras) {
	}
}
