package com.bank.trade.reporting.engine;

import java.util.logging.Logger;

import com.bank.trade.reporting.engine.exception.TradeExceptionHandler;
import com.bank.trade.reporting.engine.repository.impl.TradeDetailsRepositoryImpl;
import com.bank.trade.reporting.engine.service.TradeService;

/**
 * <p>
 * This class is the starting point in loading the trade report generation
 * application
 * </p>
 * 
 * @author debabrata
 *
 */
public class TradeReportingApp {
	private static final Logger LOGGER = Logger.getLogger(TradeReportingApp.class.getName());

	public static void main(String[] args) {
		LOGGER.info("Trade Reporting Engine application started successfully");

		TradeService tradeService = new TradeService(
				new TradeDetailsRepositoryImpl(args.length > 0 ? args[0] : null, args.length > 0 ? true : false));
		try {
			tradeService.combineReportGenerator();
		} catch (RuntimeException re) {
			LOGGER.severe(TradeExceptionHandler.getResponseWrapper(re));
		}
	}
}
