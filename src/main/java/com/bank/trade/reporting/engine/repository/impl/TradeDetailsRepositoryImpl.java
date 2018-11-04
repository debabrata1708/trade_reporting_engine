package com.bank.trade.reporting.engine.repository.impl;

import static com.bank.trade.reporting.engine.constant.Constants.BUY_FLAG;
import static com.bank.trade.reporting.engine.constant.Constants.SELL_FLAG;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import com.bank.trade.reporting.engine.exception.FileProcessingException;
import com.bank.trade.reporting.engine.model.BuySellEnum;
import com.bank.trade.reporting.engine.model.Trade;
import com.bank.trade.reporting.engine.repository.TradeDetailsRepository;
import com.bank.trade.reporting.engine.util.DateUtils;
import com.bank.trade.reporting.engine.util.TradeDataUtils;
import com.bank.trade.reporting.engine.validator.TradeDataValidator;

public class TradeDetailsRepositoryImpl implements TradeDetailsRepository {

	private final Logger LOGGER = Logger.getLogger(TradeDetailsRepositoryImpl.class.getName());

	private String fileName;
	private boolean fileNameProvided;

	public TradeDetailsRepositoryImpl(String fileName, boolean fileNameProvided) {
		this.fileName = fileName;
		this.fileNameProvided = fileNameProvided;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	@Override
	public List<Trade> fetchAllTradeDetails() {
		LOGGER.info("Trade data fetching initiated");

		List<Trade> tradeList = new ArrayList<>();

		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = getBufferedReader()) {
			br.readLine();
			while (Objects.nonNull((line = br.readLine()))) {

				String[] tradeData = line.split(cvsSplitBy);

				TradeDataValidator.validateTradeData(tradeData);

				tradeList.add(TradeDataUtils.getTradeInfo(tradeData[0], //
						getBuySellValueFromFlag(tradeData[1]), //
						Double.parseDouble(tradeData[2]), //
						tradeData[3], DateUtils.getLocalDateFromString(tradeData[4]), //
						DateUtils.getLocalDateFromString(tradeData[5]), //
						Integer.parseInt(tradeData[6]), //
						Double.parseDouble(tradeData[7])));
			}

			LOGGER.info("Trade data fetching completed successfully");
		} catch (IOException e) {
			throw new FileProcessingException("Exception occurred during processing file");
		}
		return tradeList;
	}

	private BuySellEnum getBuySellValueFromFlag(String flag) {
		switch (flag) {
		case BUY_FLAG:
			return BuySellEnum.BUY;
		case SELL_FLAG:
			return BuySellEnum.SELL;
		}
		return null;
	}

	private BufferedReader getBufferedReader() throws FileNotFoundException {
		if (fileNameProvided) {
			return new BufferedReader(new FileReader(fileName));
		} else {
			return new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName)));
		}
	}
}
