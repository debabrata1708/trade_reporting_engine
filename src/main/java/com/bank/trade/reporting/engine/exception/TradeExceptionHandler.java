package com.bank.trade.reporting.engine.exception;

public class TradeExceptionHandler {

	/**
	 * <p>
	 * This method is responsible for wrapping the exception object into an proper
	 * message to displayed in console
	 * </p>
	 * 
	 * @param exception - this parameter specifies the exception object
	 * @return - This method returns the wrapped exception message to be displayed
	 *         in console
	 */
	public static String getResponseWrapper(RuntimeException exception) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append(
				"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Exception Occurred >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		sb.append("\n");
		sb.append(exception.getMessage());
		sb.append("\n");
		sb.append(
				"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Exception StackTrace Completed>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return sb.toString();
	}
}
