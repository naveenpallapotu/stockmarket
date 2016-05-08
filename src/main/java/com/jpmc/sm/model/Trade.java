package com.jpmc.sm.model;

import java.util.Date;

public class Trade {
	private Date timeStamp;
	private TradeOptions tradeOptions;
	private int sharesQuantity;
	private double price;
	private Stock stock;
	
	public Trade(Date timeStamp, Stock stock, TradeOptions tradeOptions,
			int sharesQuantity, double price) {
		this.timeStamp = timeStamp;
		this.tradeOptions = tradeOptions;
		this.sharesQuantity = sharesQuantity;
		this.price = price;
		this.stock = stock;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public TradeOptions getTradeIndicator() {
		return tradeOptions;
	}

	public int getSharesQuantity() {
		return sharesQuantity;
	}

	public double getPrice() {
		return price;
	}
	
	public Stock getStock(){
		return stock;
	}
}
