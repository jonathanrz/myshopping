package br.com.jonathanzanella.myshopping.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.jonathanzanella.myshopping.R;
import butterknife.ButterKnife;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
	}
}