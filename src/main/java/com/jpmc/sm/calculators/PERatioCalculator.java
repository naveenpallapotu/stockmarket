package com.jpmc.sm.calculators;

public class PERatioCalculator implements StockCalculator {
	private StockCalculator ssmCalculator;

	public PERatioCalculator(StockCalculator ssmCalculator) {
		this.ssmCalculator = ssmCalculator;
	}

	@Override
	public double calculate(CalculateParameter parameters)
			throws Exception {
		double peRatio = -1.0;
		double dividend = ssmCalculator.calculate(new CalculateParameter(
				parameters.stockSymbol, parameters.tickerPrice));
		if (parameters.tickerPrice > 0.0 && dividend > 0.0) {
			peRatio = parameters.tickerPrice / dividend;
		}

		return peRatio;
	}
}
