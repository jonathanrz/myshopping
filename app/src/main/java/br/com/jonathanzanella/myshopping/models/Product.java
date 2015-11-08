package br.com.jonathanzanella.myshopping.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import br.com.jonathanzanella.myshopping.database.MyDatabase;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
@Table(databaseName = MyDatabase.NAME)
public class Product extends BaseModel {
	@Column @PrimaryKey(autoincrement = true) @Getter
	long id;

	@Column @Getter @Setter
	String name;
	@Column @Getter @Setter
	int stars;

	public static List<Product> all() {
		return initQuery().queryList();
	}

	private static From<Product> initQuery() {
		return new Select().from(Product.class);
	}

	public static Product find(long id) {
		return initQuery().where(Condition.column(Product$Table.ID).eq(id)).querySingle();
	}
}