package br.com.jonathanzanella.myshopping.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.jonathanzanella.myshopping.models.Purchase;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015 Sparta Labs. All rights reserved.
 */
public class PurchasesAdapter extends RecyclerView.Adapter<PurchasesAdapter.ViewHolder> {
	private List<Purchase> purchases;

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public ViewHolder(View itemView) {
			super(itemView);
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return purchases.size();
	}

	public void loadData() {
		purchases = Purchase.all();
	}
}
