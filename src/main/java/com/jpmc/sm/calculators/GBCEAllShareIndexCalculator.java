package com.jpmc.sm.calculators;

import java.util.List;

import com.jpmc.sm.model.Stock;
import com.jpmc.sm.repository.StockMarketRepository;

public class GBCEAllShareIndexCalculator implements OtherCalculator {
	private StockMarketRepository tradeStockRepository;

	public GBCEAllShareIndexCalculator(StockMarketRepository tradeStockRepository) {
		this.tradeStockRepository = tradeStockRepository;
	}

	@Override
	public double calculate() {
		double allShareIndex = 0.0;
		double price = 1;
		List<Stock> stocks = tradeStockRepository.getStocks();
		for (Stock stock : stocks) {
			price *= stock.getTickerPrice();
		}

		allShareIndex = Math.pow(price, 1.0 / stocks.size());
		return allShareIndex;
	}
}