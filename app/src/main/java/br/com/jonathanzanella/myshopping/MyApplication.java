package br.com.jonathanzanella.myshopping;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015 Sparta Labs. All rights reserved.
 */
public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		FlowManager.init(this);
		JodaTimeAndroid.init(this);
	}
}