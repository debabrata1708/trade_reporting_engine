package com.bank.trade.reporting.engine.util;

import java.time.LocalDate;

import com.bank.trade.reporting.engine.model.BuySellEnum;
import com.bank.trade.reporting.engine.model.Trade;

public class TradeDataUtils {

	public static Trade getTradeInfo(String tradeEntityName, BuySellEnum buySell, Double agreedFx,
			String currency, LocalDate instructionDate, LocalDate settlementDate, Integer units, Double pricePerUnit) {
		Trade trade = new Trade();
		trade.setTradeEntityName(tradeEntityName);
		trade.setBuySell(buySell);
		trade.setAgreedFx(agreedFx);
		trade.setCurrency(currency);
		trade.setInstructionDate(instructionDate);
		trade.setSettlementDate(settlementDate);
		trade.setUnits(units);
		trade.setPricePerUnit(pricePerUnit);
		return trade;
	}
}
