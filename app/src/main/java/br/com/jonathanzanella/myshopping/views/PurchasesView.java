package br.com.jonathanzanella.myshopping.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import br.com.jonathanzanella.myshopping.R;
import br.com.jonathanzanella.myshopping.activities.EditPurchaseActivity;
import br.com.jonathanzanella.myshopping.adapter.PurchasesAdapter;
import br.com.jonathanzanella.myshopping.models.Purchase;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015 Sparta Labs. All rights reserved.
 */
public class PurchasesView extends BaseView {
	private static final int REQUEST_ADD_PURCHASE = 1001;
	private PurchasesAdapter adapter;

	@Bind(R.id.view_purchases_list)
	RecyclerView purchases;

	public PurchasesView(Context context) {
		super(context);
	}

	public PurchasesView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PurchasesView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void init() {
		inflate(getContext(), R.layout.view_purchases, this);
		ButterKnife.bind(this);

		adapter = new PurchasesAdapter();
		adapter.loadData();

		purchases.setAdapter(adapter);
		purchases.setLayoutManager(new LinearLayoutManager(getContext()));
		purchases.setItemAnimator(new DefaultItemAnimator());
	}

	@OnClick(R.id.view_purchases_fab)
	void onFab() {
		Context ctx = getContext();
		Intent i = new Intent(getContext(), EditPurchaseActivity.class);
		if(ctx instanceof Activity) {
			((Activity) ctx).startActivityForResult(i, REQUEST_ADD_PURCHASE);
		} else {
			ctx.startActivity(i);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
			case REQUEST_ADD_PURCHASE:
				if(resultCode == Activity.RESULT_OK) {
					Purchase p = Purchase.find(data.getLongExtra(EditPurchaseActivity.KEY_PURCHASE_ID, 0L));
					if(p != null)
						adapter.addPurchase(p);
				}
				break;
		}
	}
}