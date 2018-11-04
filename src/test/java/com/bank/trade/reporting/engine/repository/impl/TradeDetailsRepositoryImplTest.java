package com.bank.trade.reporting.engine.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.bank.trade.reporting.engine.exception.FileProcessingException;
import com.bank.trade.reporting.engine.repository.TradeDetailsRepository;

public class TradeDetailsRepositoryImplTest {

	private TradeDetailsRepository tradeDetailsRepository;

	@Before
	public void setup() {
		tradeDetailsRepository = new TradeDetailsRepositoryImpl("abc.txt", true);
	}

	@Test
	public void test_fetchAllTradeDetails_with_FileProcessingException() {
		try {
			tradeDetailsRepository.fetchAllTradeDetails();
			fail("File processing must fail");
		} catch (FileProcessingException e) {
			assertEquals("Exception occurred during processing file", e.getMessage());
		}
	}

}
