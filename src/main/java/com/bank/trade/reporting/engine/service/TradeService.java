package com.bank.trade.reporting.engine.service;

import static com.bank.trade.reporting.engine.constant.Constants.DEFAULT_FILE_NAME;
import static com.bank.trade.reporting.engine.model.ReportType.INCOMING_TRADE_RANKING_REPORT;
import static com.bank.trade.reporting.engine.model.ReportType.INCOMING_TRADE_SETTLEMENT_REPORT;
import static com.bank.trade.reporting.engine.model.ReportType.OUTGOING_TRADE_RANKING_REPORT;
import static com.bank.trade.reporting.engine.model.ReportType.OUTGOING_TRADE_SETTLEMENT_REPORT;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.bank.trade.reporting.engine.model.ReportType;
import com.bank.trade.reporting.engine.model.Trade;
import com.bank.trade.reporting.engine.repository.impl.TradeDetailsRepositoryImpl;
import com.bank.trade.reporting.engine.util.DateUtils;

public class TradeService {

	private final Logger LOGGER = Logger.getLogger(TradeService.class.getName());
	private final TradeDetailsRepositoryImpl tradeDetailsRepository;

	public TradeService(TradeDetailsRepositoryImpl tradeDetailsRepository) {
		this.tradeDetailsRepository = tradeDetailsRepository;
	}

	/**
	 * <p>
	 * This method is responsible for combine generation of all daily trading
	 * reports
	 * </p>
	 */
	public void combineReportGenerator() {
		validateAndUpdateFileName();
		List<Trade> tradeList = tradeDetailsRepository.fetchAllTradeDetails();
		DateUtils.updateSettlementDate(tradeList);

		LOGGER.info("Settled Incoming USD amounts, settlment date wise are as follows...");
		TradeReportService incomingTradeSettlementService = getService(INCOMING_TRADE_SETTLEMENT_REPORT);
		Map<Object, Double> result = incomingTradeSettlementService.generateTradeReport(tradeList);
		result.forEach((date, settledUsdAmount) -> LOGGER
				.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + date + " : " + settledUsdAmount));

		LOGGER.info("Settled Outgoing USD amounts, settlment date wise are as follows...");
		TradeReportService outgoingTradeSettlementSevice = getService(OUTGOING_TRADE_SETTLEMENT_REPORT);
		result = outgoingTradeSettlementSevice.generateTradeReport(tradeList);
		result.forEach((date, settledUsdAmount) -> LOGGER
				.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + date + " : " + settledUsdAmount));

		LOGGER.info("Trade Basis Incoming USD amounts are as follows...");
		TradeReportService incomingTradeRankingService = getService(INCOMING_TRADE_RANKING_REPORT);
		result = incomingTradeRankingService.generateTradeReport(tradeList);
		result.forEach((tradeName, settledUsdAmount) -> LOGGER
				.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + tradeName + " : " + settledUsdAmount));

		LOGGER.info("Trade Basis Outgoing USD amounts are as follows...");
		TradeReportService outgoingTradeRankingService = getService(OUTGOING_TRADE_RANKING_REPORT);
		result = outgoingTradeRankingService.generateTradeReport(tradeList);
		result.forEach((tradeName, settledUsdAmount) -> LOGGER
				.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + tradeName + " : " + settledUsdAmount));
	}

	private void validateAndUpdateFileName() {
		String fileName = tradeDetailsRepository.getFileName();
		if (Objects.isNull(fileName)) {
			fileName = DEFAULT_FILE_NAME;
			tradeDetailsRepository.setFileName(fileName);
		}
		LOGGER.info("FileName: " + fileName);
	}

	private TradeReportService getService(ReportType reportType) {
		return TradeReportServiceFactory.getTradeReportService(reportType);
	}

}
