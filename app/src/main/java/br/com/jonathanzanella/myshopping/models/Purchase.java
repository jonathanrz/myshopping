package br.com.jonathanzanella.myshopping.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;
import java.util.List;

import br.com.jonathanzanella.myshopping.database.MyDatabase;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
@Table(databaseName = MyDatabase.NAME)
public class Purchase extends BaseModel {
	@Column @PrimaryKey(autoincrement = true)
	long id;

	@Column @Getter @Setter
	Date date;

	@Column @Getter @Setter
	String place;

	public static List<Purchase> all() {
		return initQuery().queryList();
	}

	private static From<Purchase> initQuery() {
		return new Select().from(Purchase.class);
	}
}
