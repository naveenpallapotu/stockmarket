package com.jpmc.sm.model;

import java.util.ArrayList;
import java.util.List;

public class Stock {
	private String stockSymbol;
	private StockType stockType;
	private double lastDividend;
	private double fixedDividend;
	private double parValue;
	private double tickerPrice;
	private List<Trade> trades; 

	public Stock(String stockSymbol, StockType stockType, double lastDividend,
			double fixedDividend, double parValue, double tickerPrice) {
		this.stockSymbol = stockSymbol;
		this.stockType = stockType;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
		this.tickerPrice = tickerPrice;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public StockType getStockType() {
		return stockType;
	}

	public double getLastDividend() {
		return lastDividend;
	}

	public double getFixedDividend() {
		return fixedDividend;
	}

	public double getParValue() {
		return parValue;
	}

	public double getTickerPrice() {
		return tickerPrice;
	}

	public void addTrades(Trade trade){
		if(trades == null){
			trades = new ArrayList<Trade>();
		}

		trades .add(trade);
	}

	public List<Trade> getTrades(){
		return trades;
	}

	@Override
	public String toString() {
		String pattern = "Stock Object [stockSymbol: %s, stockType: %s, lastDividend: %7.0f, fixedDividend: %7.2f, parValue: %7.2f]";
		return String.format(pattern, stockSymbol, stockType, lastDividend,
				fixedDividend, parValue);
	}
}
