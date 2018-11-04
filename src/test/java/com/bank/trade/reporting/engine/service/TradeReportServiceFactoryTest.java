package com.bank.trade.reporting.engine.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bank.trade.reporting.engine.model.ReportType;
import com.bank.trade.reporting.engine.service.impl.IncomingTradeRankingServiceImpl;
import com.bank.trade.reporting.engine.service.impl.IncomingTradeSettlementSeviceImpl;
import com.bank.trade.reporting.engine.service.impl.OutgoingTradeRankingServiceImpl;
import com.bank.trade.reporting.engine.service.impl.OutgoingTradeSettlementSeviceImpl;

public class TradeReportServiceFactoryTest {

	@Test
	public void test_getTradeReportService() {
		assertTrue(TradeReportServiceFactory.getTradeReportService(
				ReportType.INCOMING_TRADE_RANKING_REPORT) instanceof IncomingTradeRankingServiceImpl);

		assertTrue(TradeReportServiceFactory.getTradeReportService(
				ReportType.INCOMING_TRADE_SETTLEMENT_REPORT) instanceof IncomingTradeSettlementSeviceImpl);

		assertTrue(TradeReportServiceFactory.getTradeReportService(
				ReportType.OUTGOING_TRADE_RANKING_REPORT) instanceof OutgoingTradeRankingServiceImpl);

		assertTrue(TradeReportServiceFactory.getTradeReportService(
				ReportType.OUTGOING_TRADE_SETTLEMENT_REPORT) instanceof OutgoingTradeSettlementSeviceImpl);
	}

}
