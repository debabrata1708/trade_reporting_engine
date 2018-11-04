package com.bank.trade.reporting.engine.exception;

public class DataMissingException extends RuntimeException {

	private static final long serialVersionUID = 681028378552799417L;

	public DataMissingException(String message) {
		super(message);
	}
}
