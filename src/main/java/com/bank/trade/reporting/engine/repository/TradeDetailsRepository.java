package com.bank.trade.reporting.engine.repository;

import java.util.List;

import com.bank.trade.reporting.engine.model.Trade;

public interface TradeDetailsRepository {

	/**
	 * <p>
	 * The following method is responsible for fetching data from csv file to avoid
	 * the database call. In real project the following method would make a call to
	 * database to fetch trade details from there
	 * </p>
	 * 
	 * @return - the method will return list of fetched trade details
	 */
	List<Trade> fetchAllTradeDetails();
}
