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

public class OutgoingTradeSettlementSeviceImplTest {

	@Test
	public void test_generateTradeReport() {
		List<Trade> tradeList = new ArrayList<>();

		tradeList.add(TradeDataUtils.getTradeInfo("bar", BuySellEnum.BUY, 0.50, "AED",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 2), 118, 220.25));
		tradeList.add(TradeDataUtils.getTradeInfo("bar", BuySellEnum.BUY, 0.44, "SGP",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 2), 220, 80.25));
		tradeList.add(TradeDataUtils.getTradeInfo("foo", BuySellEnum.SELL, 0.40, "GBP",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 3), 100, 150.5));
		tradeList.add(TradeDataUtils.getTradeInfo("foo", BuySellEnum.BUY, 0.32, "USD",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 3), 150, 70.20));

		Map<Object, Double> result = TradeReportServiceFactory
				.getTradeReportService(ReportType.OUTGOING_TRADE_SETTLEMENT_REPORT).generateTradeReport(tradeList);
		assertEquals(2, result.size());
		Double settledUsdAmountFor2ndJan = result.get(LocalDate.of(2018, Month.JANUARY, 2));
		assertTrue(20762.95 == settledUsdAmountFor2ndJan);
		Double settledUsdAmountFor3rdJan = result.get(LocalDate.of(2018, Month.JANUARY, 3));
		assertTrue(3369.6 == settledUsdAmountFor3rdJan);
	}

}
