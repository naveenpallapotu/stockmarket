package com.jpmc.sm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jpmc.sm.model.Stock;
import com.jpmc.sm.model.StockSymbol;
import com.jpmc.sm.model.Trade;
import com.jpmc.sm.model.TradeOptions;
import com.jpmc.sm.repository.StockMarketRepository;
import com.jpmc.sm.utils.DateUtils;

public class StockMarketTestUtils {
	public static Stock getStockDetails(final String stockSymbol, final StockMarketRepository simpleStockMarketRepository) {
		Stock stock = null;
		stock = simpleStockMarketRepository.getStockBySymbol(stockSymbol);

		return stock;
	}
	
	public static List<Trade> getTradeList(final StockMarketRepository simpleStockMarketRepository) {
		List<Trade> tradeList = new ArrayList<Trade>();
		tradeList.add(getTradeData(StockSymbol.TEA, simpleStockMarketRepository));
		tradeList.add(getTradeData(StockSymbol.POP, simpleStockMarketRepository));
		tradeList.add(getTradeData(StockSymbol.ALE, simpleStockMarketRepository));
		tradeList.add(getTradeData(StockSymbol.GIN, simpleStockMarketRepository));
		tradeList.add(getTradeData(StockSymbol.JOE, simpleStockMarketRepository));
		return tradeList;
	}
	
	private static Trade getTradeData(final StockSymbol tradeStockSymbol, final StockMarketRepository simpleStockMarketRepository) {
		Trade tradeDetails = null;
		if(StockSymbol.TEA.equals(tradeStockSymbol)) {
			tradeDetails = new Trade(DateUtils.getCurrentDateTimePlusOrMinusMinutes(-30), getStockDetails(StockSymbol.TEA.toString(), simpleStockMarketRepository), TradeOptions.SELL, 20, 12.23);
		} else if(StockSymbol.POP.equals(tradeStockSymbol)) {
			tradeDetails = new Trade(DateUtils.getCurrentDateTimePlusOrMinusMinutes(-28), getStockDetails(StockSymbol.POP.toString(), simpleStockMarketRepository), TradeOptions.BUY, 80, 7.56);
		} else if(StockSymbol.ALE.equals(tradeStockSymbol)) {
			tradeDetails = new Trade(DateUtils.getCurrentDateTimePlusOrMinusMinutes(-26), getStockDetails(StockSymbol.ALE.toString(), simpleStockMarketRepository), TradeOptions.BUY, 450, 10.20);
		} else if(StockSymbol.GIN.equals(tradeStockSymbol)) {
			tradeDetails = new Trade(DateUtils.getCurrentDateTimePlusOrMinusMinutes(-26), getStockDetails(StockSymbol.GIN.toString(), simpleStockMarketRepository), TradeOptions.SELL, 50, 6.20);
		} else if(StockSymbol.JOE.equals(tradeStockSymbol)) {
			tradeDetails = new Trade(DateUtils.getCurrentDateTimePlusOrMinusMinutes(-30), getStockDetails(StockSymbol.JOE.toString(), simpleStockMarketRepository), TradeOptions.SELL, 214, 10.45);
		} 
		return tradeDetails;
	}

	public static List<Trade> getTradesBySymbol(StockSymbol tradeStockSymbol, StockMarketRepository simpleStockMarketRepository) {
		Trade tradeDetails = null;
		List<Trade> tradeList = new ArrayList<Trade>();
		if(tradeStockSymbol == StockSymbol.TEA){
			tradeDetails = new Trade(DateUtils.getCurrentDateTimePlusOrMinusMinutes(-1), getStockDetails(tradeStockSymbol.toString(), simpleStockMarketRepository), TradeOptions.SELL, 2, 10);
			tradeList.add(tradeDetails);
			tradeDetails = new Trade(DateUtils.getCurrentDateTimePlusOrMinusMinutes(-2), getStockDetails(tradeStockSymbol.toString(), simpleStockMarketRepository), TradeOptions.BUY, 3, 20);
			tradeList.add(tradeDetails);
			tradeDetails = new Trade(DateUtils.getCurrentDateTimePlusOrMinusMinutes(-3), getStockDetails(tradeStockSymbol.toString(), simpleStockMarketRepository), TradeOptions.BUY, 4, 30);
			tradeList.add(tradeDetails);
			tradeDetails = new Trade(DateUtils.getCurrentDateTimePlusOrMinusMinutes(-4), getStockDetails(tradeStockSymbol.toString(), simpleStockMarketRepository), TradeOptions.SELL, 5, 40);
			tradeList.add(tradeDetails);
			tradeDetails = new Trade(DateUtils.getCurrentDateTimePlusOrMinusMinutes(-5), getStockDetails(tradeStockSymbol.toString(), simpleStockMarketRepository), TradeOptions.SELL, 6, 50);
			tradeList.add(tradeDetails);
	
			tradeDetails = new Trade(DateUtils.getCurrentDateTimePlusOrMinusMinutes(-20), getStockDetails(tradeStockSymbol.toString(), simpleStockMarketRepository), TradeOptions.SELL, 7, 60);
			tradeList.add(tradeDetails);
		}else if(tradeStockSymbol == StockSymbol.POP){
			tradeDetails = new Trade(DateUtils.getCurrentDateTimePlusOrMinusMinutes(-1), getStockDetails(tradeStockSymbol.toString(), simpleStockMarketRepository), TradeOptions.SELL, 20, 100);
			tradeList.add(tradeDetails);
			tradeDetails = new Trade(DateUtils.getCurrentDateTimePlusOrMinusMinutes(-2), getStockDetails(tradeStockSymbol.toString(), simpleStockMarketRepository), TradeOptions.BUY, 30, 200);
			tradeList.add(tradeDetails);
			tradeDetails = new Trade(DateUtils.getCurrentDateTimePlusOrMinusMinutes(-3), getStockDetails(tradeStockSymbol.toString(), simpleStockMarketRepository), TradeOptions.BUY, 40, 300);
			tradeList.add(tradeDetails);
			tradeDetails = new Trade(DateUtils.getCurrentDateTimePlusOrMinusMinutes(-4), getStockDetails(tradeStockSymbol.toString(), simpleStockMarketRepository), TradeOptions.SELL, 50, 400);
			tradeList.add(tradeDetails);
			tradeDetails = new Trade(DateUtils.getCurrentDateTimePlusOrMinusMinutes(-5), getStockDetails(tradeStockSymbol.toString(), simpleStockMarketRepository), TradeOptions.SELL, 60, 500);
			tradeList.add(tradeDetails);
	
			tradeDetails = new Trade(DateUtils.getCurrentDateTimePlusOrMinusMinutes(-20), getStockDetails(tradeStockSymbol.toString(), simpleStockMarketRepository), TradeOptions.SELL, 70, 60);
			tradeList.add(tradeDetails);
		}

		return tradeList;
	}

	public static List<String> getStockSymbols() {
		String[] stockSymbols = {"TEA", "POP", "ALE", "GIN", "JOE"};
		return Arrays.asList(stockSymbols);
	}
}
