package br.com.jonathanzanella.myshopping.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.jonathanzanella.myshopping.R;
import br.com.jonathanzanella.myshopping.models.Product;
import br.com.jonathanzanella.myshopping.models.Purchase;
import br.com.jonathanzanella.myshopping.models.PurchaseItem;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
public class PurchasesItemAdapter extends RecyclerView.Adapter<PurchasesItemAdapter.ViewHolder> {
	private List<PurchaseItem> items;

	public static class ViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.row_purchase_item_product_name)
		TextView productName;
		@Bind(R.id.row_purchase_item_quantity)
		TextView quantity;
		@Bind(R.id.row_purchase_item_amount)
		TextView amount;
		@Bind(R.id.row_purchase_item_total)
		TextView total;

		public ViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);
		}

		public void setData(final PurchaseItem item) {
			quantity.setText(NumberFormat.getNumberInstance(new Locale("pt", "BR")).format(item.getQuantity()));
			amount.setText(NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(item.getAmount() / 100.0));
			total.setText(NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(item.getTotal() / 100.0));
			Product p = item.getProduct();
			if(p != null)
				productName.setText(p.getName());
			else
				productName.setText(R.string.no_product);
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_purchase_item, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.setData(items.get(position));
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	public void loadData(@NonNull Purchase purchase) {
		items = PurchaseItem.purchaseItems(purchase.getId());
	}

	public void addItem(@NonNull PurchaseItem item) {
		items.add(item);
		notifyItemInserted(items.size() - 1);
	}
}
