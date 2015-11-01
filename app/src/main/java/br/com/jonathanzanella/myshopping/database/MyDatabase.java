package br.com.jonathanzanella.myshopping.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {
	public static final String NAME = "MyShopping";

	public static final int VERSION = 1;
}