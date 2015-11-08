package br.com.jonathanzanella.myshopping.adapter;

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
import br.com.jonathanzanella.myshopping.models.Product;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jonathan on 01/11/15.
 * Copyright (c) 2015 Sparta Labs. All rights reserved.
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
	private List<Product> products;

	public static class ViewHolder extends RecyclerView.ViewHolder {
		private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		@Bind(R.id.row_products_name)
		TextView name;
		@Bind(R.id.row_products_stars)
		TextView stars;

		public ViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);
		}

		public void setData(Product product) {
			name.setText(product.getName());
			stars.setText(String.valueOf(product.getStars()));
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_products, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.setData(products.get(position));
	}

	@Override
	public int getItemCount() {
		return products.size();
	}

	public void loadData() {
		products = Product.all();
	}

	public void addProduct(@NonNull Product product) {
		products.add(product);
		notifyItemInserted(products.size() - 1);
	}
}
