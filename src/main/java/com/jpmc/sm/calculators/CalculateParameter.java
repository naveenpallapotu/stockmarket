package com.jpmc.sm.calculators;

public class CalculateParameter {
	public String stockSymbol;
	public double tickerPrice;

	public CalculateParameter(String stockSymbol, double tickerPrice) {
		this.stockSymbol = stockSymbol;
		this.tickerPrice = tickerPrice;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public double getTickerPrice() {
		return tickerPrice;
	}

}