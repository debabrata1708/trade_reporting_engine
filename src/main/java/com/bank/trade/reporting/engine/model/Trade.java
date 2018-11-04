package com.bank.trade.reporting.engine.model;

import java.time.LocalDate;

public class Trade {
	private String tradeEntityName;
	private BuySellEnum buySell;
	private Double agreedFx;
	private String currency;
	private LocalDate instructionDate;
	private LocalDate settlementDate;
	private Integer units;
	private Double pricePerUnit;

	public String getTradeEntityName() {
		return tradeEntityName;
	}

	public void setTradeEntityName(String tradeEntityName) {
		this.tradeEntityName = tradeEntityName;
	}

	public BuySellEnum getBuySell() {
		return buySell;
	}

	public void setBuySell(BuySellEnum buySell) {
		this.buySell = buySell;
	}

	public Double getAgreedFx() {
		return agreedFx;
	}

	public void setAgreedFx(Double agreedFx) {
		this.agreedFx = agreedFx;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public Double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	@Override
	public String toString() {
		return "Trade [tradeEntityName=" + tradeEntityName + ", buySell=" + buySell + ", agreedFx=" + agreedFx
				+ ", currency=" + currency + ", instructionDate=" + instructionDate + ", settlementDate="
				+ settlementDate + ", units=" + units + ", pricePerUnit=" + pricePerUnit + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agreedFx == null) ? 0 : agreedFx.hashCode());
		result = prime * result + ((buySell == null) ? 0 : buySell.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((instructionDate == null) ? 0 : instructionDate.hashCode());
		result = prime * result + ((pricePerUnit == null) ? 0 : pricePerUnit.hashCode());
		result = prime * result + ((settlementDate == null) ? 0 : settlementDate.hashCode());
		result = prime * result + ((tradeEntityName == null) ? 0 : tradeEntityName.hashCode());
		result = prime * result + ((units == null) ? 0 : units.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		if (agreedFx == null) {
			if (other.agreedFx != null)
				return false;
		} else if (!agreedFx.equals(other.agreedFx))
			return false;
		if (buySell != other.buySell)
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (instructionDate == null) {
			if (other.instructionDate != null)
				return false;
		} else if (!instructionDate.equals(other.instructionDate))
			return false;
		if (pricePerUnit == null) {
			if (other.pricePerUnit != null)
				return false;
		} else if (!pricePerUnit.equals(other.pricePerUnit))
			return false;
		if (settlementDate == null) {
			if (other.settlementDate != null)
				return false;
		} else if (!settlementDate.equals(other.settlementDate))
			return false;
		if (tradeEntityName == null) {
			if (other.tradeEntityName != null)
				return false;
		} else if (!tradeEntityName.equals(other.tradeEntityName))
			return false;
		if (units == null) {
			if (other.units != null)
				return false;
		} else if (!units.equals(other.units))
			return false;
		return true;
	}

}
