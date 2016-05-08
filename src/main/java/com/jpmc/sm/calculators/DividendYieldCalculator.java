package com.jpmc.sm.calculators;

import com.jpmc.sm.model.Stock;
import com.jpmc.sm.model.StockType;
import com.jpmc.sm.repository.StockMarketRepository;

public class DividendYieldCalculator implements StockCalculator {
	private StockMarketRepository tradeStockRepository;

	public DividendYieldCalculator(StockMarketRepository tradeStockRepository) {
		this.tradeStockRepository = tradeStockRepository;
	}

	@Override
	public double calculate(CalculateParameter parameters)
			throws Exception {
		double dividendYield = -1.0;

		Stock stock = tradeStockRepository.getStockBySymbol(parameters.stockSymbol);

		if (parameters.tickerPrice > 0.0) {
			if (stock.getStockType() == StockType.COMMON) {
				dividendYield = stock.getLastDividend() / parameters.tickerPrice;
			} else {
				dividendYield = (stock.getFixedDividend() * stock.getParValue())
						/ parameters.tickerPrice;
			}
		}

		return dividendYield;
	}
}
