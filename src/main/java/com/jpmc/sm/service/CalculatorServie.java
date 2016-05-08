package com.jpmc.sm.service;

import com.jpmc.sm.calculators.CalculateParameter;

public interface CalculatorServie {
	public double calculateDividendYied(CalculateParameter parameters) throws Exception;
	
	public double calculatePERatio(CalculateParameter parameters) throws Exception;
	
	public double calculateVolumeWeightStockPrice(CalculateParameter parameters) throws Exception;
	
	public double calculateGBCEAllShareIndex() throws Exception;
}
