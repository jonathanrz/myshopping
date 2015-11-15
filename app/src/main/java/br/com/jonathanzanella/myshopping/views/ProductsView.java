package br.com.jonathanzanella.myshopping.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import br.com.jonathanzanella.myshopping.R;
import br.com.jonathanzanella.myshopping.activities.EditProductActivity;
import br.com.jonathanzanella.myshopping.adapter.ProductsAdapter;
import br.com.jonathanzanella.myshopping.models.Product;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jonathan on 08/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
public class ProductsView extends BaseView {
	private static final int REQUEST_ADD_PRODUCTS = 1001;
	private ProductsAdapter adapter;

	@Bind(R.id.view_products_list)
	RecyclerView products;

	public ProductsView(Context context) {
		super(context);
	}

	public ProductsView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ProductsView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void init() {
		inflate(getContext(), R.layout.view_products, this);
		ButterKnife.bind(this);

		adapter = new ProductsAdapter();
		adapter.loadData();

		products.setAdapter(adapter);
		products.setLayoutManager(new LinearLayoutManager(getContext()));
		products.setItemAnimator(new DefaultItemAnimator());
	}

	@OnClick(R.id.view_products_fab)
	void onFab() {
		Context ctx = getContext();
		Intent i = new Intent(getContext(), EditProductActivity.class);
		if(ctx instanceof Activity) {
			((Activity) ctx).startActivityForResult(i, REQUEST_ADD_PRODUCTS);
		} else {
			ctx.startActivity(i);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
			case REQUEST_ADD_PRODUCTS:
				if(resultCode == Activity.RESULT_OK) {
					Product p = Product.find(data.getLongExtra(EditProductActivity.KEY_PRODUCT_ID, 0L));
					if(p != null)
						adapter.addProduct(p);
				}
				break;
		}
	}
}
