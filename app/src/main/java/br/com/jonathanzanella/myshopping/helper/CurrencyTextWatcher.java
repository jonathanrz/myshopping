package br.com.jonathanzanella.myshopping.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by jonathan on 04/11/15.
 * Copyright (c) 2015. All rights reserved.
 */
public class CurrencyTextWatcher implements TextWatcher {
	private String currentAmount;
	private EditText amount;

	public CurrencyTextWatcher(EditText amount) {
		this.amount = amount;
		currentAmount = amount.getText().toString();
	}

	@Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
	@Override public void afterTextChanged(Editable s) {}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if(!s.toString().equals(currentAmount)){
			amount.removeTextChangedListener(this);

			String cleanString = cleanString(s.toString());

			long parsed = Long.parseLong(cleanString.trim());
			String formatted = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(parsed / 100.0);

			currentAmount = formatted;
			amount.setText(formatted);
			amount.setSelection(formatted.length());

			amount.addTextChangedListener(this);
		}
	}

	public String cleanString(String s) {
		return s.replaceAll("[R$,.]", "");
	}
}
