package com.bank.trade.reporting.engine.validator;

import static com.bank.trade.reporting.engine.constant.Constants.DATE_FORMAT;
import static com.bank.trade.reporting.engine.constant.Constants.FIELD_AGREED_FX;
import static com.bank.trade.reporting.engine.constant.Constants.FIELD_BUY_SELL;
import static com.bank.trade.reporting.engine.constant.Constants.FIELD_CURRENCY;
import static com.bank.trade.reporting.engine.constant.Constants.FIELD_INSTRUCTION_DATE;
import static com.bank.trade.reporting.engine.constant.Constants.FIELD_PRICE_PER_UNIT;
import static com.bank.trade.reporting.engine.constant.Constants.FIELD_SETTLEMENT_DATE;
import static com.bank.trade.reporting.engine.constant.Constants.FIELD_UNITS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.bank.trade.reporting.engine.exception.DataMissingException;
import com.bank.trade.reporting.engine.exception.InvalidDataFormatException;

public class TradeDataValidatorTest {

	@Test
	public void test_DataMissingException() {
		String[] tradeData = { "bar", "", "0.75", "AED", "01-Jan-2018", "02-Jan-2018", "118", "220.25" };
		try {
			TradeDataValidator.validateTradeData(tradeData);
			fail("Exception must occur due to invalid date format");
		} catch (DataMissingException e) {
			assertEquals("Data Missing in the required fields", e.getMessage());
		}
	}
	
	@Test
	public void test_valid_data() {
		String[] tradeData = { "bar", "B", "0.75", "AED", "01-Jan-2018", "02-Jan-2018", "118", "220.25" };
		try {
			TradeDataValidator.validateTradeData(tradeData);
		} catch (DataMissingException e) {
			fail("Exception must not occur");
		}
	}

	@Test
	public void test_InvalidDataFormatException_for_buySell() {
		String[] tradeData = { "bar", "E", "0.75", "AED", "01-Jan-2018", "02-Jan-2018", "118", "220.25" };
		try {
			TradeDataValidator.validateTradeData(tradeData);
			fail("Exception must occur due to invalid date format");
		} catch (InvalidDataFormatException e) {
			assertEquals("Field: " + FIELD_BUY_SELL + ", Invalid value exist in " + FIELD_BUY_SELL + " must be B or S",
					e.getMessage());
		}
	}

	@Test
	public void test_InvalidDataFormatException_for_agreedFx() {
		String[] tradeData = { "bar", "B", "0.7s5", "AED", "01-Jan-2018", "02-Jan-2018", "118", "220.25" };
		try {
			TradeDataValidator.validateTradeData(tradeData);
			fail("Exception must occur due to invalid date format");
		} catch (InvalidDataFormatException e) {
			assertEquals("Field: " + FIELD_AGREED_FX + ", Invalid value exist in " + FIELD_AGREED_FX
					+ " must be a double value", e.getMessage());
		}
	}

	@Test
	public void test_InvalidDataFormatException_for_currency() {
		String[] tradeData = { "bar", "B", "0.75", "ASED", "01-Jan-2018", "02-Jan-2018", "118", "220.25" };
		try {
			TradeDataValidator.validateTradeData(tradeData);
			fail("Exception must occur due to invalid date format");
		} catch (InvalidDataFormatException e) {
			assertEquals(
					"Field: " + FIELD_CURRENCY + ", Invalid value exist in " + FIELD_CURRENCY + " must be 3 characters",
					e.getMessage());
		}
	}

	@Test
	public void test_InvalidDataFormatException_for_instruction_date() {
		String[] tradeData = { "bar", "B", "0.75", "AED", "2018-Jan-01", "02-Jan-2018", "118", "220.25" };
		try {
			TradeDataValidator.validateTradeData(tradeData);
			fail("Exception must occur due to invalid date format");
		} catch (InvalidDataFormatException e) {
			assertEquals("Field: " + FIELD_INSTRUCTION_DATE + ", Invalid value exist in " + FIELD_INSTRUCTION_DATE
					+ " must be in " + DATE_FORMAT + " format", e.getMessage());
		}
	}

	@Test
	public void test_InvalidDataFormatException_for_settlement_date() {
		String[] tradeData = { "bar", "B", "0.75", "AED", "01-Jan-2018", "2018-Jan-01", "118", "220.25" };
		try {
			TradeDataValidator.validateTradeData(tradeData);
			fail("Exception must occur due to invalid date format");
		} catch (InvalidDataFormatException e) {
			assertEquals("Field: " + FIELD_SETTLEMENT_DATE + ", Invalid value exist in " + FIELD_SETTLEMENT_DATE
					+ " must be in " + DATE_FORMAT + " format", e.getMessage());
		}
	}

	@Test
	public void test_InvalidDataFormatException_for_units() {
		String[] tradeData = { "bar", "B", "0.75", "AED", "01-Jan-2018", "02-Jan-2018", "118s", "220.25" };
		try {
			TradeDataValidator.validateTradeData(tradeData);
			fail("Exception must occur due to invalid date format");
		} catch (InvalidDataFormatException e) {
			assertEquals(
					"Field: " + FIELD_UNITS + ", Invalid value exist in " + FIELD_UNITS + " must be an integer value",
					e.getMessage());
		}
	}

	@Test
	public void test_InvalidDataFormatException_for_price_per_unit() {
		String[] tradeData = { "bar", "B", "0.75", "AED", "01-Jan-2018", "02-Jan-2018", "118", "220.2s5" };
		try {
			TradeDataValidator.validateTradeData(tradeData);
			fail("Exception must occur due to invalid date format");
		} catch (InvalidDataFormatException e) {
			assertEquals("Field: " + FIELD_PRICE_PER_UNIT + ", Invalid value exist in " + FIELD_PRICE_PER_UNIT
					+ " must be a double value", e.getMessage());
		}
	}

}
