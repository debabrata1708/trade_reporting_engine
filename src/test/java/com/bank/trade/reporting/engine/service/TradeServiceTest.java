package com.bank.trade.reporting.engine.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class TradeServiceTest {

	private TradeService tradeService;

	@Before
	public void setUp() {
		tradeService = mock(TradeService.class);
	}

	@Test
	public void test_combineReportGenerator_with_mock_object() {
		tradeService.combineReportGenerator();
		verify(tradeService, times(1)).combineReportGenerator();
	}

}
