package com.jpmc.sm.predicate;

import java.util.Calendar;

import org.apache.commons.collections.Predicate;

import com.jpmc.sm.model.Trade;

/**
 * A Predicate is the object equivalent of an if statement. It uses the input
 * object to return a true or false value, and is often used in validation or
 * filtering.
 * 
 */
public class StockPredicate implements Predicate {
	private String stockSymbol = "";
	private Calendar dateRange = null;

	public StockPredicate(final String stockSymbol, final int minutesRange) {
		this.stockSymbol = stockSymbol;
		if (minutesRange > 0) {
			dateRange = Calendar.getInstance();
			dateRange.add(Calendar.MINUTE, -minutesRange);
		}
	}

	public boolean evaluate(final Object tradeObject) {
		Trade trade = (Trade) tradeObject;
		boolean shouldBeInclude = trade.getStock().getStockSymbol()
				.equals(stockSymbol);
		if (shouldBeInclude && dateRange != null) {
			shouldBeInclude = dateRange.getTime().compareTo(
					trade.getTimeStamp()) <= 0;
		}
		return shouldBeInclude;
	}
}