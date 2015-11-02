package br.com.jonathanzanella.myshopping.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import br.com.jonathanzanella.myshopping.R;
import butterknife.ButterKnife;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015 Sparta Labs. All rights reserved.
 */
public class PurchasesView extends FrameLayout {
	public PurchasesView(Context context) {
		super(context);
		init();
	}

	public PurchasesView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public PurchasesView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public PurchasesView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	private void init() {
		inflate(getContext(), R.layout.view_purchases, this);
		ButterKnife.bind(this);
	}
}