package com.jpmc.sm.service;

import com.jpmc.sm.model.Trade;
import com.jpmc.sm.repository.StockMarketRepository;

/**
 * Service class used to implement the Trade and Stock calculation functions.
 *
 */
public class StockMarketServiceImpl implements StockMarketService {

	private StockMarketRepository tradeStockRepository;

	public StockMarketServiceImpl(final StockMarketRepository tradeStockRepository){
		this.tradeStockRepository = tradeStockRepository;
	}

	public boolean recordTrade(final String stockSymbol, final Trade trade) throws Exception{
		boolean recordResult = false;
		try{
			recordResult = tradeStockRepository.recordTrade(stockSymbol, trade);
		}catch(Exception exception){
			throw new Exception("Error when trying to record a trade.", exception);
		}
		return recordResult;
	}
}
