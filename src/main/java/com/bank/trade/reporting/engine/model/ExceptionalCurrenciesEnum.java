package com.bank.trade.reporting.engine.model;

import java.util.Arrays;

public enum ExceptionalCurrenciesEnum {
	AED, SAR;

	/**
	 * <p>
	 * The purpose of this method is to check whether any currency sent falls under
	 * any currencies available in this enum
	 * </p>
	 * 
	 * @param receivedCurrency - this parameter specifies the currency to be checked
	 * 
	 * @return - this method returns true if currency exist in the enum otherwise
	 *         false
	 */
	public static boolean contains(String receivedCurrency) {
		return Arrays.asList(values()).stream().filter(e -> e.toString().equals(receivedCurrency)).findAny()
				.isPresent();
	}
}
