package com.codex.service;

import java.util.List;

import com.codex.modal.Coin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


public interface CoinService {
	List<Coin> getCoinList(int page) throws JsonMappingException, JsonProcessingException, Exception;
	
	String getMarketChart(String coinId,int days) throws Exception;
	
	String getCoinDetails(String coinId) throws Exception;
	
	Coin findById(String coinId) throws Exception;
	
	String searchCoin(String keyword) throws Exception;
	
	String getTop50CoinsByMarketCapRank() throws Exception;

	String getTreadingCoins() throws Exception;
}
