package com.jpmc.sm.service;

import com.jpmc.sm.model.Trade;

public interface StockMarketService {
	public boolean recordTrade(String stockSymbol, Trade trade) throws Exception;
}