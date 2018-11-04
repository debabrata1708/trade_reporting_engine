package com.bank.trade.reporting.engine.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.bank.trade.reporting.engine.model.BuySellEnum;
import com.bank.trade.reporting.engine.model.ReportType;
import com.bank.trade.reporting.engine.model.Trade;
import com.bank.trade.reporting.engine.service.TradeReportServiceFactory;
import com.bank.trade.reporting.engine.util.TradeDataUtils;

public class IncomingTradeRankingServiceImplTest {

	@Test
	public void test_generateTradeReport() {
		String TRADE_NAME_BAR = "bar";
		String TRADE_NAME_FOO = "foo";

		List<Trade> tradeList = new ArrayList<>();

		tradeList.add(TradeDataUtils.getTradeInfo(TRADE_NAME_BAR, BuySellEnum.SELL, 0.50, "AED",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 2), 118, 220.25));
		tradeList.add(TradeDataUtils.getTradeInfo(TRADE_NAME_BAR, BuySellEnum.SELL, 0.44, "SGP",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 2), 220, 80.25));
		tradeList.add(TradeDataUtils.getTradeInfo(TRADE_NAME_FOO, BuySellEnum.BUY, 0.40, "GBP",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 3), 100, 150.5));
		tradeList.add(TradeDataUtils.getTradeInfo(TRADE_NAME_FOO, BuySellEnum.SELL, 0.32, "USD",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 3), 150, 70.20));

		Map<Object, Double> result = TradeReportServiceFactory
				.getTradeReportService(ReportType.INCOMING_TRADE_RANKING_REPORT).generateTradeReport(tradeList);
		assertEquals(2, result.size());
		Double settledUsdAmountForBar = result.get(TRADE_NAME_BAR);
		assertTrue(20762.95 == settledUsdAmountForBar);
		Double settledUsdAmountForFoo = result.get(TRADE_NAME_FOO);
		assertTrue(3369.6 == settledUsdAmountForFoo);
		Object[] keyArr = result.keySet().toArray();
		assertTrue(keyArr[0].equals(TRADE_NAME_BAR));
		assertTrue(keyArr[1].equals(TRADE_NAME_FOO));
	}

}
