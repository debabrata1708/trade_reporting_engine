package com.bank.trade.reporting.engine.util;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bank.trade.reporting.engine.model.BuySellEnum;
import com.bank.trade.reporting.engine.model.Trade;

public class DateUtilsTest {

	@Test
	public void test_updateSettlementDate_on_Friday_for_SGP_currency() {
		List<Trade> tradeList = new ArrayList<>();
		tradeList.add(TradeDataUtils.getTradeInfo("bar", BuySellEnum.SELL, 0.44, "SAR",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 5), 220, 80.25));
		DateUtils.updateSettlementDate(tradeList);
		assertTrue(tradeList.size() == 1);
		assertTrue(LocalDate.of(2018, Month.JANUARY, 7).compareTo(tradeList.get(0).getSettlementDate()) == 0);
	}

	@Test
	public void test_updateSettlementDate_on_Saturday_for_SGP_currency() {
		List<Trade> tradeList = new ArrayList<>();
		tradeList.add(TradeDataUtils.getTradeInfo("bar", BuySellEnum.SELL, 0.44, "SAR",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 6), 220, 80.25));
		DateUtils.updateSettlementDate(tradeList);
		assertTrue(tradeList.size() == 1);
		assertTrue(LocalDate.of(2018, Month.JANUARY, 7).compareTo(tradeList.get(0).getSettlementDate()) == 0);
	}

	@Test
	public void test_updateSettlementDate_on_non_Friday_Saturday_for_SGP_currency() {
		List<Trade> tradeList = new ArrayList<>();
		tradeList.add(TradeDataUtils.getTradeInfo("bar", BuySellEnum.SELL, 0.44, "SGP",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 8), 220, 80.25));
		DateUtils.updateSettlementDate(tradeList);
		assertTrue(tradeList.size() == 1);
		assertTrue(LocalDate.of(2018, Month.JANUARY, 8).compareTo(tradeList.get(0).getSettlementDate()) == 0);
	}

	@Test
	public void test_updateSettlementDate_on_Friday_for_AED_currency() {
		List<Trade> tradeList = new ArrayList<>();
		tradeList.add(TradeDataUtils.getTradeInfo("bar", BuySellEnum.SELL, 0.44, "AED",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 5), 220, 80.25));
		DateUtils.updateSettlementDate(tradeList);
		assertTrue(tradeList.size() == 1);
		assertTrue(LocalDate.of(2018, Month.JANUARY, 7).compareTo(tradeList.get(0).getSettlementDate()) == 0);
	}

	@Test
	public void test_updateSettlementDate_on_Saturday_for_AED_currency() {
		List<Trade> tradeList = new ArrayList<>();
		tradeList.add(TradeDataUtils.getTradeInfo("bar", BuySellEnum.SELL, 0.44, "AED",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 6), 220, 80.25));
		DateUtils.updateSettlementDate(tradeList);
		assertTrue(tradeList.size() == 1);
		assertTrue(LocalDate.of(2018, Month.JANUARY, 7).compareTo(tradeList.get(0).getSettlementDate()) == 0);
	}

	@Test
	public void test_updateSettlementDate_on_non_Friday_Saturday_for_AED_currency() {
		List<Trade> tradeList = new ArrayList<>();
		tradeList.add(TradeDataUtils.getTradeInfo("bar", BuySellEnum.SELL, 0.44, "AED",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 8), 220, 80.25));
		DateUtils.updateSettlementDate(tradeList);
		assertTrue(tradeList.size() == 1);
		assertTrue(LocalDate.of(2018, Month.JANUARY, 8).compareTo(tradeList.get(0).getSettlementDate()) == 0);
	}

	@Test
	public void test_updateSettlementDate_on_Saturday_for_non_SGP_AED_currency() {
		List<Trade> tradeList = new ArrayList<>();
		tradeList.add(TradeDataUtils.getTradeInfo("bar", BuySellEnum.SELL, 0.44, "INR",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 6), 220, 80.25));
		DateUtils.updateSettlementDate(tradeList);
		assertTrue(tradeList.size() == 1);
		assertTrue(LocalDate.of(2018, Month.JANUARY, 8).compareTo(tradeList.get(0).getSettlementDate()) == 0);
	}

	@Test
	public void test_updateSettlementDate_on_Sunday_for_non_SGP_AED_currency() {
		List<Trade> tradeList = new ArrayList<>();
		tradeList.add(TradeDataUtils.getTradeInfo("bar", BuySellEnum.SELL, 0.44, "INR",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 7), 220, 80.25));
		DateUtils.updateSettlementDate(tradeList);
		assertTrue(tradeList.size() == 1);
		assertTrue(LocalDate.of(2018, Month.JANUARY, 8).compareTo(tradeList.get(0).getSettlementDate()) == 0);
	}

	@Test
	public void test_updateSettlementDate_on_non_Saturday_Sunday_for_non_SGP_AED_currency() {
		List<Trade> tradeList = new ArrayList<>();
		tradeList.add(TradeDataUtils.getTradeInfo("bar", BuySellEnum.SELL, 0.44, "INR",
				LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.JANUARY, 8), 220, 80.25));
		DateUtils.updateSettlementDate(tradeList);
		assertTrue(tradeList.size() == 1);
		assertTrue(LocalDate.of(2018, Month.JANUARY, 8).compareTo(tradeList.get(0).getSettlementDate()) == 0);
	}

	@Test
	public void test_getLocalDateFromString_with_null_input() {
		assertNull(DateUtils.getLocalDateFromString(null));
	}

	@Test
	public void test_getLocalDateFromString_with_invalid_format_input() {
		assertNull(DateUtils.getLocalDateFromString("2018-Nov-11"));
	}

	@Test
	public void test_getLocalDateFromString_with_valid_format_input() {
		assertTrue(
				DateUtils.getLocalDateFromString("11-Nov-2018").compareTo(LocalDate.of(2018, Month.NOVEMBER, 11)) == 0);
	}

}
