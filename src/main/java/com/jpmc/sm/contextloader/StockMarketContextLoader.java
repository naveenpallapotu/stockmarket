package com.jpmc.sm.contextloader;

public interface StockMarketContextLoader {

	public StockMarketContextLoader INSTANCE = StockMarketContextLoaderImpl.getInstance();

	public <T extends Object> T getBean(String beanName, Class<T> objectClass);
}
