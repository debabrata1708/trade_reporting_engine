package com.bank.trade.reporting.engine.validator;

import static com.bank.trade.reporting.engine.constant.Constants.DATE_FORMAT;
import static com.bank.trade.reporting.engine.constant.Constants.FIELD_AGREED_FX;
import static com.bank.trade.reporting.engine.constant.Constants.FIELD_BUY_SELL;
import static com.bank.trade.reporting.engine.constant.Constants.FIELD_CURRENCY;
import static com.bank.trade.reporting.engine.constant.Constants.FIELD_INSTRUCTION_DATE;
import static com.bank.trade.reporting.engine.constant.Constants.FIELD_PRICE_PER_UNIT;
import static com.bank.trade.reporting.engine.constant.Constants.FIELD_SETTLEMENT_DATE;
import static com.bank.trade.reporting.engine.constant.Constants.FIELD_UNITS;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import com.bank.trade.reporting.engine.exception.DataMissingException;
import com.bank.trade.reporting.engine.exception.InvalidDataFormatException;
import com.bank.trade.reporting.engine.model.BuySellEnum;
import com.bank.trade.reporting.engine.util.DateUtils;

public class TradeDataValidator {

	private static final TradeDataValidator tradeDataValidator;

	static {
		tradeDataValidator = new TradeDataValidator();
	}

	public static void validateTradeData(String[] tradeData) {
		tradeDataValidator.validateValuesPresent(tradeData);
		tradeDataValidator.validateBuySell(tradeData[1], FIELD_BUY_SELL);
		tradeDataValidator.validateDoubleField(tradeData[2], FIELD_AGREED_FX);
		tradeDataValidator.validateCurrency(tradeData[3], FIELD_CURRENCY);
		tradeDataValidator.validateDate(tradeData[4], FIELD_INSTRUCTION_DATE);
		tradeDataValidator.validateDate(tradeData[5], FIELD_SETTLEMENT_DATE);
		tradeDataValidator.validateIntegerField(tradeData[6], FIELD_UNITS);
		tradeDataValidator.validateDoubleField(tradeData[7], FIELD_PRICE_PER_UNIT);
	}

	private void validateValuesPresent(String[] tradeData) {
		boolean emptyData = Arrays.asList(tradeData).stream().filter(eachFieldData -> isEmptyData(eachFieldData))
				.findFirst().isPresent();
		if (emptyData) {
			throw new DataMissingException("Data Missing in the required fields");
		}
	}

	private void validateBuySell(String buySellStr, String fieldName) {
		Optional<BuySellEnum> buySell = Arrays.asList(BuySellEnum.values()).stream()
				.filter(val -> val.getFlag().equals(buySellStr.toUpperCase())).findFirst();
		if (!buySell.isPresent()) {
			throwInvalidDataFormatException(
					"Field: " + fieldName + ", Invalid value exist in " + fieldName + " must be B or S");
		}
	}

	private void validateDoubleField(String fieldValue, String fieldName) {
		try {
			Double.parseDouble(fieldValue);
		} catch (NumberFormatException e) {
			throwInvalidDataFormatException(
					"Field: " + fieldName + ", Invalid value exist in " + fieldName + " must be a double value");
		}
	}

	private void validateIntegerField(String fieldValue, String fieldName) {
		try {
			Integer.parseInt(fieldValue);
		} catch (NumberFormatException e) {
			throwInvalidDataFormatException(
					"Field: " + fieldName + ", Invalid value exist in " + fieldName + " must be an integer value");
		}
	}

	private void validateCurrency(String currency, String fieldName) {
		if (currency.length() != 3) {
			throwInvalidDataFormatException(
					"Field: " + fieldName + ", Invalid value exist in " + fieldName + " must be 3 characters");
		}
	}

	private void validateDate(String date, String fieldName) {
		if (Objects.isNull(DateUtils.getLocalDateFromString(date))) {
			throwInvalidDataFormatException("Field: " + fieldName + ", Invalid value exist in " + fieldName
					+ " must be in " + DATE_FORMAT + " format");
		}
	}

	private void throwInvalidDataFormatException(String message) {
		throw new InvalidDataFormatException(message);
	}

	private static boolean isEmptyData(String data) {
		return Objects.isNull(data) || "".equals(data.trim());
	}
}
