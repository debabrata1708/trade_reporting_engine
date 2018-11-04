package com.bank.trade.reporting.engine.service;

import com.bank.trade.reporting.engine.model.ReportType;
import com.bank.trade.reporting.engine.service.impl.IncomingTradeRankingServiceImpl;
import com.bank.trade.reporting.engine.service.impl.IncomingTradeSettlementSeviceImpl;
import com.bank.trade.reporting.engine.service.impl.OutgoingTradeRankingServiceImpl;
import com.bank.trade.reporting.engine.service.impl.OutgoingTradeSettlementSeviceImpl;

public class TradeReportServiceFactory {

	/**
	 * <p>
	 * This method return required trade report service based on the type of report
	 * </p>
	 * 
	 * @param reportType - this parameter specifies the type of the report
	 * 
	 * @return - This method will return specified trade service based on the
	 *         provided report type
	 */
	public static TradeReportService getTradeReportService(ReportType reportType) {
		switch (reportType) {
		case INCOMING_TRADE_RANKING_REPORT:
			return new IncomingTradeRankingServiceImpl();
		case INCOMING_TRADE_SETTLEMENT_REPORT:
			return new IncomingTradeSettlementSeviceImpl();
		case OUTGOING_TRADE_RANKING_REPORT:
			return new OutgoingTradeRankingServiceImpl();
		case OUTGOING_TRADE_SETTLEMENT_REPORT:
			return new OutgoingTradeSettlementSeviceImpl();
		}
		return null;
	}
}
