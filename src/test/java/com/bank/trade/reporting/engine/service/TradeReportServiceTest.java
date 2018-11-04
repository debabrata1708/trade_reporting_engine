package com.bank.trade.reporting.engine.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import com.bank.trade.reporting.engine.model.BuySellEnum;
import com.bank.trade.reporting.engine.model.ReportType;
import com.bank.trade.reporting.engine.model.Trade;
import com.bank.trade.reporting.engine.util.TradeDataUtils;

public class TradeReportServiceTest {

	@Test
	public void test_isTradeTypeMatched() {
		TradeReportService tradeReportService = TradeReportServiceFactory
				.getTradeReportService(ReportType.INCOMING_TRADE_RANKING_REPORT);
		Trade trade = TradeDataUtils.getTradeInfo("bar", BuySellEnum.SELL, 0.44, "SGP",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 5), 220, 80.25);
		assertTrue(tradeReportService.isTradeTypeMatched(trade, BuySellEnum.SELL));
	}

	@Test
	public void test_isTradeTypeMisMatched() {
		TradeReportService tradeReportService = TradeReportServiceFactory
				.getTradeReportService(ReportType.INCOMING_TRADE_RANKING_REPORT);
		Trade trade = TradeDataUtils.getTradeInfo("bar", BuySellEnum.SELL, 0.44, "SGP",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 5), 220, 80.25);
		assertFalse(tradeReportService.isTradeTypeMatched(trade, BuySellEnum.BUY));
	}

}
