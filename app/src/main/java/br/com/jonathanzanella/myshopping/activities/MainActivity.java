package br.com.jonathanzanella.myshopping.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import br.com.jonathanzanella.myshopping.R;
import br.com.jonathanzanella.myshopping.views.PurchasesView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
public class MainActivity extends AppCompatActivity {
	@Bind(R.id.act_main_content) FrameLayout content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		addViewToContent(new PurchasesView(this));
	}

	private void addViewToContent(View child) {
		content.removeAllViews();
		child.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
													ViewGroup.LayoutParams.MATCH_PARENT));
		content.addView(child);
	}
}