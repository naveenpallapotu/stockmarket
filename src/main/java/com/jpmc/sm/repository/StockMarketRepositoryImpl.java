package com.jpmc.sm.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jpmc.sm.model.Stock;
import com.jpmc.sm.model.Trade;

/**
 * Local Session entity class to load and save all stock data and acts like persistence class  
 *
 */
public class StockMarketRepositoryImpl implements StockMarketRepository {
	private Map<String, Stock> stocks = null;

	public boolean recordTrade(final String stockSymbol, final Trade trade) throws Exception{
		boolean result = false;
		Stock stock = null;

		stock = getStockBySymbol(stockSymbol);
		if(stock != null){
			stock.addTrades(trade);
			stocks.put(stockSymbol, stock);
			result = true;
		}

		return result;
	}

	public Stock getStockBySymbol(final String stockSymbol){
		Stock stock = null;
		if(stockSymbol!=null){
			stock = stocks.get(stockSymbol);
		}

		return stock;
	}

	@Override
	public List<Trade> getTrades(final String stockSymbol) {
		List<Trade> trades = null;
		
		Stock stock = stocks.get(stockSymbol);
		if(stock != null){
			trades = stock.getTrades();
		}
		
		return trades == null ? new ArrayList<Trade>(0) : trades;
	}

	@Override
	public List<Stock> getStocks() {
		return new ArrayList<Stock>(stocks.values());
	}

	public void addStock(Stock stock){
		if (stocks == null){
			stocks = new ConcurrentHashMap<String, Stock>();
		}

		stocks.put(stock.getStockSymbol(), stock);
	}
}