package com.bank.trade.reporting.engine.util;

import static com.bank.trade.reporting.engine.constant.Constants.DATE_FORMAT;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.bank.trade.reporting.engine.model.ExceptionalCurrenciesEnum;
import com.bank.trade.reporting.engine.model.Trade;

public class DateUtils {

	/**
	 * 
	 * The purpose of the method is to update the settlement date present in the
	 * each trade object of tradeList based on following rules
	 * 
	 * <br>
	 * <br>
	 * 
	 * If trade object contains among specified currencies like AED, SGP then if for
	 * that trade settlement date is a FRIDAY or SATURDAY then it must be settled on
	 * SUNDAY.
	 * 
	 * <br>
	 * <br>
	 * 
	 * If trade object contains other than specified currencies like JPY, INR then
	 * if for that trade settlement date is a SATURDAY or SUNDAY then it must be
	 * settled on MONDAY.
	 * 
	 * @param tradeList - this parameter specifies the list of trades for which
	 *                  settlement date needs to be checked and based on the above
	 *                  mentioned rule if required needs to be updated to proper
	 *                  settlement date
	 */
	public static void updateSettlementDate(List<Trade> tradeList) {
		tradeList.stream().filter(trade -> ExceptionalCurrenciesEnum.contains(trade.getCurrency())).filter(
				trade -> isSuppliedDateSpecificDays(trade.getSettlementDate(), DayOfWeek.FRIDAY, DayOfWeek.SATURDAY))
				.forEach(trade -> trade.setSettlementDate(
						updateDateWithNextSpecifiedDay(trade.getSettlementDate(), DayOfWeek.SUNDAY)));

		tradeList.stream().filter(trade -> !ExceptionalCurrenciesEnum.contains(trade.getCurrency())).filter(
				trade -> isSuppliedDateSpecificDays(trade.getSettlementDate(), DayOfWeek.SATURDAY, DayOfWeek.SUNDAY))
				.forEach(trade -> trade.setSettlementDate(
						updateDateWithNextSpecifiedDay(trade.getSettlementDate(), DayOfWeek.MONDAY)));
	}

	/**
	 * <p>
	 * The purpose of this method is to check whether the {@code LocalDate} provided
	 * falls under any specified days of week
	 * </p>
	 * 
	 * @param date               - this parameter specifies the date to be checked
	 * @param requiredDaysOfWeek - this parameter specifies the days of week
	 * 
	 * @return - this method returns true or false. If date falls under the
	 *         specified days of the week then return true else false
	 */
	public static boolean isSuppliedDateSpecificDays(LocalDate date, DayOfWeek... requiredDaysOfWeek) {
		return Arrays.asList(requiredDaysOfWeek).stream().filter(day -> day == date.getDayOfWeek()).findFirst()
				.isPresent();
	}

	/**
	 * <p>
	 * The purpose of this method is to update the given {@code LocalDate} with the
	 * next date of specified day of the week
	 * </p>
	 * 
	 * @param date      - this parameter specifies the date to be updated
	 * @param dayOfWeek - this parameter specifies the day of the week with which
	 *                  date to be updated
	 * 
	 * @return - the method returns the updated date
	 */
	public static LocalDate updateDateWithNextSpecifiedDay(LocalDate date, DayOfWeek dayOfWeek) {
		return date.with(TemporalAdjusters.next(dayOfWeek));
	}

	/**
	 * <p>
	 * The following method is responsible for generating LocalDate from a given
	 * string value
	 * </p>
	 * 
	 * @param dateStr - This parameter specifies the date value in string format
	 * 
	 * @return - this method will return converted LocalDate value
	 */
	public static LocalDate getLocalDateFromString(String dateStr) {
		if (Objects.isNull(dateStr)) {
			return null;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		try {
			return LocalDate.parse(dateStr, formatter);
		} catch (DateTimeParseException ex) {
			return null;
		}
	}
}
