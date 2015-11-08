package br.com.jonathanzanella.myshopping.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import br.com.jonathanzanella.myshopping.R;
import br.com.jonathanzanella.myshopping.views.BaseView;
import br.com.jonathanzanella.myshopping.views.ProductsView;
import br.com.jonathanzanella.myshopping.views.PurchasesView;
import butterknife.Bind;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

	@Bind(R.id.act_main_drawer)
	DrawerLayout drawer;
	@Bind(R.id.act_main_navigation_view)
	NavigationView navigationView;
	@Bind(R.id.act_main_content)
	FrameLayout content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
				this, drawer, toolbar,
				R.string.navigation_drawer_open, R.string.navigation_drawer_close
		);

		addViewToContent(new PurchasesView(this));
		navigationView.setNavigationItemSelectedListener(this);
		drawer.setDrawerListener(drawerToggle);
		drawerToggle.syncState();
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

	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_shopping:
				addViewToContent(new PurchasesView(this));
				setTitle(R.string.shopping);
				drawer.closeDrawers();
				return true;
			case R.id.menu_products:
				addViewToContent(new ProductsView(this));
				setTitle(R.string.products);
				drawer.closeDrawers();
				return true;
		}
		return false;
	}
}