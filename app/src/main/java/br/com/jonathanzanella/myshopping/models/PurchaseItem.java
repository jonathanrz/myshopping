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
public class PurchaseItem extends BaseModel {
	@Column @PrimaryKey(autoincrement = true) @Getter
	long id;

	@Column @Setter
	long purchaseId;
	@Column @Setter
	long productId;

	@Column @Getter @Setter
	long amount;

	private static From<PurchaseItem> initQuery() {
		return new Select().from(PurchaseItem.class);
	}

	public static PurchaseItem find(long id) {
		return initQuery().where(Condition.column(PurchaseItem$Table.ID).eq(id)).querySingle();
	}

	public static List<PurchaseItem> all() {
		return initQuery().queryList();
	}

	public Purchase getPurchase() {
		return Purchase.find(purchaseId);
	}

	public Product getProduct() {
		return Product.find(productId);
	}
}
