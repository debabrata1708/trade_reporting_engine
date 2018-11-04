package com.bank.trade.reporting.engine.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bank.trade.reporting.engine.model.BuySellEnum;
import com.bank.trade.reporting.engine.model.Trade;
import com.bank.trade.reporting.engine.service.TradeReportService;

public class IncomingTradeRankingServiceImpl implements TradeReportService {

	@Override
	public Map<Object, Double> generateTradeReport(List<Trade> tradeList) {
		Map<String, Double> groupedTradeMap = tradeList.stream()
				.filter(trade -> isTradeTypeMatched(trade, BuySellEnum.SELL))
				.collect(Collectors.groupingBy(Trade::getTradeEntityName,
						Collectors.summingDouble(e -> (e.getUnits() * e.getPricePerUnit() * e.getAgreedFx()))));

		Map<Object, Double> sortedTradeMap = groupedTradeMap.entrySet().stream()
				.sorted(Collections.reverseOrder(Comparator.comparing(Map.Entry::getValue)))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		return sortedTradeMap;
	}

}
