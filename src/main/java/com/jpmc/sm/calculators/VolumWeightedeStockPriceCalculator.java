package com.jpmc.sm.calculators;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

import com.jpmc.sm.model.Trade;
import com.jpmc.sm.predicate.StockPredicate;
import com.jpmc.sm.repository.StockMarketRepository;

public class VolumWeightedeStockPriceCalculator implements StockCalculator {
	private static final int TIME_INTERVALFOR_VWSP_15 = 15;
	private StockMarketRepository tradeStockRepository;

	public VolumWeightedeStockPriceCalculator(
			StockMarketRepository tradeStockRepository) {
		this.tradeStockRepository = tradeStockRepository;
	}

	@Override
	public double calculate(CalculateParameter parameters)
			throws Exception {
		double stockPrice = 0.0;

		stockPrice = calculateStockPriceinRange(parameters.stockSymbol, TIME_INTERVALFOR_VWSP_15);

		return stockPrice;	}

	private double calculateStockPriceinRange(final String stockSymbol, final int minutesRange) throws Exception{
		double stockPrice = 0.0;
		@SuppressWarnings("unchecked")
		Collection<Trade> trades = CollectionUtils.select(tradeStockRepository.getTrades(stockSymbol), new StockPredicate(stockSymbol, minutesRange));
		double shareQuantityAcum = 0.0;
		double tradePriceAcum = 0.0;
		for(Trade trade : trades){
			tradePriceAcum += (trade.getPrice() * trade.getSharesQuantity());
			shareQuantityAcum += trade.getSharesQuantity();
		}
		if(shareQuantityAcum > 0.0){
			stockPrice = tradePriceAcum / shareQuantityAcum;	
		}
		return stockPrice;
	}
}
