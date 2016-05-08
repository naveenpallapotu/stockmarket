package com.jpmc.sm;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jpmc.sm.calculators.CalculateParameter;
import com.jpmc.sm.contextloader.StockMarketContextLoader;
import com.jpmc.sm.model.Stock;
import com.jpmc.sm.model.StockSymbol;
import com.jpmc.sm.model.StockType;
import com.jpmc.sm.model.Trade;
import com.jpmc.sm.repository.StockMarketRepository;
import com.jpmc.sm.repository.StockMarketRepositoryImpl;
import com.jpmc.sm.service.CalculatorServiceImpl;
import com.jpmc.sm.service.CalculatorServie;
import com.jpmc.sm.service.StockMarketService;
import com.jpmc.sm.service.StockMarketServiceImpl;

public class StockMarketTest {

	private StockMarketService simpleStockMarketService;
	private StockMarketRepository simpleStockMarketRepository;
	private StockMarketContextLoader simpleStockContextLoader;
	private CalculatorServie calculatorService;
	@Before
	public void setup() {
		simpleStockContextLoader = StockMarketContextLoader.INSTANCE;
		simpleStockMarketService = simpleStockContextLoader.getBean("simpleStockMarketService", StockMarketServiceImpl.class);
		simpleStockMarketRepository = simpleStockContextLoader.getBean("tradeStockRepository", StockMarketRepositoryImpl.class);
		calculatorService = simpleStockContextLoader.getBean("calculatorService", CalculatorServiceImpl.class); 

		simpleStockMarketRepository.addStock(new Stock("TEA", StockType.COMMON, 0, 0, 100, 10));
		simpleStockMarketRepository.addStock(new Stock("POP", StockType.COMMON, 8, 0, 100, 15));
		simpleStockMarketRepository.addStock(new Stock("ALE", StockType.COMMON, 23, 0, 60, 9));
		simpleStockMarketRepository.addStock(new Stock("GIN", StockType.PREFERRED, 8, 0.02, 100, 50));
		simpleStockMarketRepository.addStock(new Stock("JOE", StockType.COMMON, 13, 0, 250, 62));
	}

	@Test
	public void recordATradeTest() throws Exception{
		List<Trade> tradeList = StockMarketTestUtils.getTradeList(simpleStockMarketRepository);
		Assert.assertNotNull(tradeList);
		int tradesNumber = simpleStockMarketRepository.getTrades("TEA").size();
		Assert.assertEquals(tradesNumber, 0);
		for(Trade trade: tradeList){
			boolean result = simpleStockMarketService.recordTrade("TEA", trade);
			Assert.assertTrue(result);
		}

		tradesNumber = simpleStockMarketRepository.getTrades("TEA").size();
		Assert.assertEquals(tradesNumber, tradeList.size());
	}	

	@Test
	public void calculateDividendYieldTest() throws Exception{
		String stockSymbol = "TEA";
		double dividendYield = calculatorService.calculateDividendYied(new CalculateParameter(stockSymbol, 10));
		Assert.assertEquals(stockSymbol + " dividend yield not matching", dividendYield, 0, 0);

		stockSymbol = "POP";
		dividendYield = calculatorService.calculateDividendYied(new CalculateParameter(stockSymbol, 15));
		Assert.assertEquals(stockSymbol + " dividend yield not matching", dividendYield, 0.5333333333333333, 0);

		stockSymbol = "ALE";
		dividendYield = calculatorService.calculateDividendYied(new CalculateParameter(stockSymbol, 20));
		Assert.assertEquals(stockSymbol + " dividend yield not matching", dividendYield, 1.15, 0);

		stockSymbol = "GIN";
		dividendYield = calculatorService.calculateDividendYied(new CalculateParameter(stockSymbol, 10));
		Assert.assertEquals(stockSymbol + " dividend yield not matching", dividendYield, 0.2, 0);

		stockSymbol = "JOE";
		dividendYield = calculatorService.calculateDividendYied(new CalculateParameter(stockSymbol, 30));
		Assert.assertEquals(stockSymbol + " dividend yield not matching", dividendYield, 0.43333333333333335, 0);
	}

	@Test
	public void calculatePERatioTest() throws Exception{
		String stockSymbol = "TEA";
		double peRatio = calculatorService.calculatePERatio(new CalculateParameter(stockSymbol, 10.0));
		Assert.assertEquals(stockSymbol + " PERatio not matching", peRatio, -1.0, 0);
		
		stockSymbol = "POP";
		peRatio = calculatorService.calculatePERatio(new CalculateParameter(stockSymbol, 15.0));
		Assert.assertEquals(stockSymbol + " PERatio not matching", peRatio, 28.125, 0);

		stockSymbol = "ALE";
		peRatio = calculatorService.calculatePERatio(new CalculateParameter(stockSymbol, 20.0));
		Assert.assertEquals(stockSymbol + " PERatio not matching", peRatio, 17.39130434782609, 0);

		stockSymbol = "GIN";
		peRatio = calculatorService.calculatePERatio(new CalculateParameter(stockSymbol, 10.0));
		Assert.assertEquals(stockSymbol + " PERatio not matching", peRatio, 50.0, 0);

		stockSymbol = "JOE";
		peRatio = calculatorService.calculatePERatio(new CalculateParameter(stockSymbol, 30.0));
		Assert.assertEquals(stockSymbol + " PERatio not matching", peRatio, 69.23076923076923, 0);
	}

	@Test
	public void calculateVolumeWeightedStockPrice() throws Exception{
		String stockSymbol = "TEA";
		List<Trade> tradeList = StockMarketTestUtils.getTradesBySymbol(StockSymbol.TEA, simpleStockMarketRepository);

		for(Trade trade: tradeList){
			boolean result = simpleStockMarketService.recordTrade("TEA", trade);
			Assert.assertTrue(result);
		}
		double volumeWeightedStockPrice = calculatorService.calculateVolumeWeightStockPrice(new CalculateParameter(stockSymbol, 0));
		Assert.assertEquals(stockSymbol + " VolumeWeightedStockPrice", volumeWeightedStockPrice, 35.0, 0);

		stockSymbol = "POP";
		tradeList = StockMarketTestUtils.getTradesBySymbol(StockSymbol.POP, simpleStockMarketRepository);

		for(Trade trade: tradeList){
			boolean result = simpleStockMarketService.recordTrade(stockSymbol, trade);
			Assert.assertTrue(result);
		}

		volumeWeightedStockPrice = calculatorService.calculateVolumeWeightStockPrice(new CalculateParameter(stockSymbol, 0));
		Assert.assertEquals(stockSymbol + " VolumeWeightedStockPrice", volumeWeightedStockPrice, 350.0, 0);
	}

	@Test
	public void calculateGBCEAllShareIndexTest() throws Exception{
		double GBCEAllShareIndex = calculatorService.calculateGBCEAllShareIndex();
		Assert.assertEquals("GBCEAllShareIndex not matching ", GBCEAllShareIndex, 21.1027518705059, 0);
	}

}
