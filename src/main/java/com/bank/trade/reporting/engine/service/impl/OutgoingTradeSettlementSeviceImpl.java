package com.bank.trade.reporting.engine.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bank.trade.reporting.engine.model.BuySellEnum;
import com.bank.trade.reporting.engine.model.Trade;
import com.bank.trade.reporting.engine.service.TradeReportService;

public class OutgoingTradeSettlementSeviceImpl implements TradeReportService {

	@Override
	public Map<Object, Double> generateTradeReport(List<Trade> tradeList) {
		Map<Object, Double> result = tradeList.stream().filter(trade -> isTradeTypeMatched(trade, BuySellEnum.BUY))
				.collect(Collectors.groupingBy(Trade::getSettlementDate,
						Collectors.summingDouble(e -> (e.getUnits() * e.getPricePerUnit() * e.getAgreedFx()))));

		return result;
	}

}
