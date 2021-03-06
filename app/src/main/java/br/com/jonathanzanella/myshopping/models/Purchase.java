package br.com.jonathanzanella.myshopping.models;

import android.support.annotation.Nullable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
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
	@Column @PrimaryKey(autoincrement = true) @Getter
	long id;

	@Column @Getter @Setter
	Date date;

	@Column @Getter @Setter
	long placeId;

	public static List<Purchase> all() {
		return initQuery().queryList();
	}

	private static From<Purchase> initQuery() {
		return new Select().from(Purchase.class);
	}

	public static Purchase find(long id) {
		return initQuery().where(Condition.column(Purchase$Table.ID).eq(id)).querySingle();
	}

	public @Nullable Place getPlace() {
		return Place.find(placeId);
	}
}