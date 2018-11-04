package com.bank.trade.reporting.engine.exception;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TradeExceptionHandlerTest {

	@Test
	public void test_getResponseWrapper() {
		final String EXPECTED_MESSAGE_START = "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Exception Occurred >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
		final String EXPECTED_MESSAGE_END = "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Exception StackTrace Completed>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
		String actualMessage = TradeExceptionHandler.getResponseWrapper(new DataMissingException("data missing"));
		assertTrue(actualMessage.contains(EXPECTED_MESSAGE_START));
		assertTrue(actualMessage.contains(EXPECTED_MESSAGE_END));
	}

}
