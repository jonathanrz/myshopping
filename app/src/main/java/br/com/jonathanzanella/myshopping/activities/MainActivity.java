package br.com.jonathanzanella.myshopping.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import br.com.jonathanzanella.myshopping.R;
import br.com.jonathanzanella.myshopping.views.BaseView;
import br.com.jonathanzanella.myshopping.views.PurchasesView;
import butterknife.Bind;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
public class MainActivity extends BaseActivity {
	@Bind(R.id.act_main_content) FrameLayout content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		addViewToContent(new PurchasesView(this));
	}

	private void addViewToContent(View child) {
		content.removeAllViews();
		child.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
													ViewGroup.LayoutParams.MATCH_PARENT));
		content.addView(child);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		for(int i = 0; i < content.getChildCount(); i++) {
			View v = content.getChildAt(i);
			if(v instanceof BaseView)
				((BaseView)v).onActivityResult(requestCode, resultCode, data);
		}
	}
}