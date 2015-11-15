package br.com.jonathanzanella.myshopping.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import br.com.jonathanzanella.myshopping.R;
import br.com.jonathanzanella.myshopping.activities.DetailPurchaseActivity;
import br.com.jonathanzanella.myshopping.models.Place;
import br.com.jonathanzanella.myshopping.models.Purchase;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
public class PurchasesAdapter extends RecyclerView.Adapter<PurchasesAdapter.ViewHolder> {
	private List<Purchase> purchases;

	public static class ViewHolder extends RecyclerView.ViewHolder {
		private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		@Bind(R.id.row_purchase_date)
		TextView date;
		@Bind(R.id.row_purchase_place)
		TextView place;

		public ViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);
		}

		public void setData(final Purchase purchase) {
			date.setText(sdf.format(purchase.getDate()));
			Place p = purchase.getPlace();
			if(p != null)
				place.setText(p.getName());
			else
				place.setText(R.string.no_places);

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(v.getContext(), DetailPurchaseActivity.class);
					i.putExtra(DetailPurchaseActivity.KEY_PURCHASE_ID, purchase.getId());
					v.getContext().startActivity(i);
				}
			});
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_purchase, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.setData(purchases.get(position));
	}

	@Override
	public int getItemCount() {
		return purchases.size();
	}

	public void loadData() {
		purchases = Purchase.all();
	}

	public void addPurchase(@NonNull Purchase purchase) {
		purchases.add(purchase);
		notifyItemInserted(purchases.size() - 1);
	}
}
