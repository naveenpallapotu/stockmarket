package com.jpmc.sm.repository;

import java.util.List;

import com.jpmc.sm.model.Stock;
import com.jpmc.sm.model.Trade;

public interface StockMarketRepository {
	public void addStock(Stock stock);
	public boolean recordTrade(String stockSymbol, Trade trade) throws Exception;
	public List<Trade> getTrades(String stockSymbol);
	public Stock getStockBySymbol(String stockSymbol);
	public List<Stock> getStocks();
}
