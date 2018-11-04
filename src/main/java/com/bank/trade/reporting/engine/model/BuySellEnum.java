package com.bank.trade.reporting.engine.model;

public enum BuySellEnum {
	BUY("B"), SELL("S");

	private String flag;

	private BuySellEnum(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}
}
