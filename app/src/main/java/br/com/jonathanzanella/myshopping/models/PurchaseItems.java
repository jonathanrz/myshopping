package br.com.jonathanzanella.myshopping.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import br.com.jonathanzanella.myshopping.database.MyDatabase;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
@Table(databaseName = MyDatabase.NAME)
public class PurchaseItems extends BaseModel {
	@Column @PrimaryKey(autoincrement = true)
	long id;

	@Column
	long purchaseId;
	@Column
	long productId;

	@Column @Getter @Setter
	long amount;
}
