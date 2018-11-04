package com.bank.trade.reporting.engine.util;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import com.bank.trade.reporting.engine.model.BuySellEnum;
import com.bank.trade.reporting.engine.model.Trade;

public class TradeDataUtilsTest {

	@Test
	public void test() {
		String tradeEntityName = "bar";
		BuySellEnum buySell = BuySellEnum.SELL;
		Double agreedFx = 0.44;
		String currency = "SAR";
		LocalDate instructionDate = LocalDate.of(2018, Month.JANUARY, 1);
		LocalDate settlementDate = LocalDate.of(2018, Month.JANUARY, 5);
		Integer units = 220;
		Double pricePerUnit = 80.25;

		Trade expectedTrade = new Trade();
		expectedTrade.setTradeEntityName(tradeEntityName);
		expectedTrade.setBuySell(buySell);
		expectedTrade.setAgreedFx(agreedFx);
		expectedTrade.setCurrency(currency);
		expectedTrade.setInstructionDate(instructionDate);
		expectedTrade.setSettlementDate(settlementDate);
		expectedTrade.setUnits(units);
		expectedTrade.setPricePerUnit(pricePerUnit);
		Trade receivedTrade = TradeDataUtils.getTradeInfo(tradeEntityName, buySell, agreedFx, currency, instructionDate,
				settlementDate, units, pricePerUnit);

		assertEquals(expectedTrade.toString(), receivedTrade.toString());
	}

}
