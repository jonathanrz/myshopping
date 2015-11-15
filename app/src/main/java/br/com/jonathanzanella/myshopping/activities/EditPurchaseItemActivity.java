package br.com.jonathanzanella.myshopping.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.jonathanzanella.myshopping.R;
import br.com.jonathanzanella.myshopping.adapter.SelectableProductsAdapter;
import br.com.jonathanzanella.myshopping.helper.CurrencyTextWatcher;
import br.com.jonathanzanella.myshopping.models.Product;
import br.com.jonathanzanella.myshopping.models.Purchase;
import br.com.jonathanzanella.myshopping.models.PurchaseItem;
import butterknife.Bind;

/**
 * Created by jonathan on 08/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
public class EditPurchaseItemActivity extends BaseActivity {
	public static final String KEY_PURCHASE_ID = "KeyPurchaseId";
	public static final String KEY_PURCHASE_ITEM_ID = "KeyPurchaseItemId";
	private static final String LOG_TAG = "EditPurchaseItemAct";
	@Bind(R.id.act_edit_purchase_item_quantity)
	EditText editQuantity;
	@Bind(R.id.act_edit_purchase_item_amount)
	EditText editAmount;
	@Bind(R.id.act_edit_purchase_item_product)
	RecyclerView listProducts;

	private SelectableProductsAdapter productsAdapter;
	private Purchase purchase;
	private CurrencyTextWatcher currencyTextWatcher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_purchase_item);

		setTitle(R.string.add_new_item);
	}

	@Override
	protected void storeBundle(Bundle extras) {
		super.storeBundle(extras);
		if(extras == null)
			return;

		long purchaseId = extras.getLong(KEY_PURCHASE_ID);
		if(purchaseId != 0)
			purchase = Purchase.find(purchaseId);

		long purchaseItemId = extras.getLong(KEY_PURCHASE_ITEM_ID);
		if(purchaseItemId != 0)
			purchase = PurchaseItem.find(purchaseId).getPurchase();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		if(purchase == null) {
			Log.e(LOG_TAG, "called detail purchase item activity without purchase");
			finish();
			return;
		}

		productsAdapter = new SelectableProductsAdapter();
		productsAdapter.loadData();
		listProducts.setLayoutManager(new GridLayoutManager(this, 2));
		listProducts.setAdapter(productsAdapter);

		currencyTextWatcher = new CurrencyTextWatcher(editAmount);
		editAmount.addTextChangedListener(currencyTextWatcher);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.save, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_save:
				save();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void save() {
		PurchaseItem item = new PurchaseItem();
		item.setAmount(Long.parseLong(currencyTextWatcher.cleanString(editAmount.getText().toString())));
		item.setQuantity(Double.parseDouble(editQuantity.getText().toString()));
		item.setPurchaseId(purchase.getId());
		Product p = productsAdapter.getSelectedProduct();
		if(p != null)
			item.setProductId(p.getId());
		item.save();

		Intent i = new Intent();
		i.putExtra(KEY_PURCHASE_ITEM_ID, item.getId());
		setResult(RESULT_OK, i);
		finish();
	}
}
