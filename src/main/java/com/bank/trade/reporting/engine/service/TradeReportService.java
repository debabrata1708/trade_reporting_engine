package com.bank.trade.reporting.engine.service;

import java.util.List;
import java.util.Map;

import com.bank.trade.reporting.engine.model.BuySellEnum;
import com.bank.trade.reporting.engine.model.Trade;

public interface TradeReportService {

	/**
	 * <p>
	 * The method will generate required daily trade report based on the received
	 * trade details
	 * </p>
	 * 
	 * @param tradeList - this parameter specifies the trade details to be used for
	 *                  generating daily trade report
	 * 
	 * @return - this method will return the generated report params in the form of
	 *         a map
	 */
	Map<Object, Double> generateTradeReport(List<Trade> tradeList);

	/**
	 * <p>
	 * The purpose of this method is to check whether the buySell value exist under
	 * the trade object matches with the specified buySell value
	 * </p>
	 * 
	 * @param trade   - This parameter specifies the trade object under which
	 *                buySell value is there
	 * @param buySell - This parameter specifies the buySell value to be checked
	 * 
	 * @return - this method returns true if buySell value matched with the buySell
	 *         value of the trade otherwise false
	 */
	default boolean isTradeTypeMatched(Trade trade, BuySellEnum buySell) {
		return trade.getBuySell() == buySell;
	}
}
