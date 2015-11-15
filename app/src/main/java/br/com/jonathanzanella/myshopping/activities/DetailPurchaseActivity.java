package br.com.jonathanzanella.myshopping.activities;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import br.com.jonathanzanella.myshopping.R;
import br.com.jonathanzanella.myshopping.models.Place;
import br.com.jonathanzanella.myshopping.models.Purchase;
import butterknife.Bind;

/**
 * Created by jonathan on 08/11/15.
 * Copyright (c) 2015 Sparta Labs. All rights reserved.
 */
public class DetailPurchaseActivity extends BaseActivity {
	public static final String KEY_PURCHASE_ID = "KeyPurchaseId";
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
	private static final String LOG_TAG = "DetailPurchaseActivity";
	@Bind(R.id.act_detail_purchase_date)
	TextView textDate;
	@Bind(R.id.act_detail_purchase_place)
	TextView textPlace;
	@Bind(R.id.act_detail_purchase_items)
	RecyclerView items;

	private Purchase purchase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_purchase);
	}

	@Override
	protected void storeBundle(Bundle extras) {
		if(extras == null)
			return;

		long purchaseId = extras.getLong(KEY_PURCHASE_ID);
		if(purchaseId != 0)
			purchase = Purchase.find(purchaseId);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		if(purchase == null) {
			Log.e(LOG_TAG, "called detail purchase activity without purchase");
			finish();
			return;
		}

		setStatic(textDate, R.string.date, sdf.format(purchase.getDate()));
		Place p = purchase.getPlace();
		setStatic(textPlace, R.string.place, p != null ? p.getName() : getString(R.string.no_places));
	}

	private void setStatic(TextView tv, @StringRes int sttRes, String value) {
		String stt = getString(sttRes);
		SpannableString content = new SpannableString(stt + ": " + value);
		int colorPrimary = getResources().getColor(R.color.colorPrimary);
		content.setSpan(new ForegroundColorSpan(colorPrimary), 0, stt.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tv.setText(content);
	}
}
