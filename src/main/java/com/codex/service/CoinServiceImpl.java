package com.codex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.codex.modal.Coin;
import com.codex.repository.CoinRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CoinServiceImpl implements CoinService {

	@Autowired
	private CoinRepository coinRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	//@Autowired
	@Override
	public List<Coin> getCoinList(int page) throws Exception {
		String url = "https://pro-api.coingecko.com/api/v3/coins/markets?vs_currency=usd&per_page=10&page="+page;
		
		//fetch data 
		RestTemplate restTemplate=  new RestTemplate();
		
		try {
			HttpHeaders headers=new HttpHeaders();
			
			HttpEntity<String> entity=new HttpEntity<String>("parameters",headers);
			
			ResponseEntity<String> response=restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
			
			List<Coin> coinList=objectMapper.readValue(response.getBody(), new TypeReference<List<Coin>>(){});
		
			return coinList;
			
		}catch (HttpClientErrorException | HttpServerErrorException e){
			throw new Exception(e.getMessage());
		}
		
	}

	
	@Override
	public String getMarketChart(String coinId, int days) throws Exception {
		
		String url = "https://pro-api.coingecko.com/api/v3/coins/"+coinId+"/market_chart?vs_currency=usd&days="+days;
		
		//fetch data 
		RestTemplate restTemplate=  new RestTemplate();
		
		try {
			HttpHeaders headers=new HttpHeaders();
			
			HttpEntity<String> entity=new HttpEntity<String>("parameters",headers);
			
			ResponseEntity<String> response=restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
		
			return response.getBody();
			
		}catch (HttpClientErrorException | HttpServerErrorException e){
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String getCoinDetails(String coinId) throws Exception {
		String url = "https://pro-api.coingecko.com/api/v3/coins/"+coinId;
		
		//fetch data 
		RestTemplate restTemplate=  new RestTemplate();
		
		try {
			HttpHeaders headers=new HttpHeaders();
			
			HttpEntity<String> entity=new HttpEntity<String>("parameters",headers);
			
			ResponseEntity<String> response=restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
		
			//to save the data into database
			JsonNode jsonNode=objectMapper.readTree(response.getBody());
			
			Coin coin=new Coin();
			
			coin.setId(jsonNode.get("id").asText());
			coin.setName(jsonNode.get("name").asText());
			coin.setSymbol(jsonNode.get("symbol").asText());
			coin.setImage(jsonNode.get("image").get("large").asText());
			
			JsonNode marketData=jsonNode.get("market_data");
			
			coin.setCurrentPrice(marketData.get("current_price").get("usd").asDouble());
			coin.setMarketCap(marketData.get("market_cap").get("usd").asLong());
			coin.setMarketCapRank(marketData.get("market_cap_rank").asInt());
			coin.setTotalVolume(marketData.get("total_volume").get("usd").asLong());
			coin.setHigh24h(marketData.get("high_24h").get("usd").asDouble());
			coin.setLow24h(marketData.get("low_24h").get("usd").asDouble());
			coin.setPriceChange24h(marketData.get("price_chagne2_4h").get("usd").asDouble());
			coin.setMarketCapChange24h(marketData.get("price_change2_4h").get("usd").asLong());
			coin.setPriceChangePercentage24h(marketData.get("price_change2_4h").get("usd").asDouble());
			coin.setMarketCapChange24h(marketData.get("market_cap_change2_4h").get("usd").asLong());
			coin.setMarketCapChangePercentage24h(marketData.get("market_cap_change_percentage_24h").asLong());
			coin.setTotalSupply(marketData.get("total_supply").asLong());
			
			coinRepository.save(coin);
			
			return response.getBody();
			
		}catch (HttpClientErrorException | HttpServerErrorException e){
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Coin findById(String coinId) throws Exception {
		Optional<Coin> optionalCoin=coinRepository.findById(coinId);
		if(optionalCoin.isEmpty()) {
			throw new Exception("coin not found");
		}
		
		return optionalCoin.get();
	}

	@Override
	public String searchCoin(String keyword) throws Exception {
		String url = "https://pro-api.coingecko.com/api/v3/seach?query="+keyword;
		
		//fetch data 
		RestTemplate restTemplate=  new RestTemplate();
		
		try {
			HttpHeaders headers=new HttpHeaders();
			
			HttpEntity<String> entity=new HttpEntity<String>("parameters",headers);
			
			ResponseEntity<String> response=restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
		
			return response.getBody();
			
		}catch (HttpClientErrorException | HttpServerErrorException e){
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String getTop50CoinsByMarketCapRank() throws Exception {
String url = "https://pro-api.coingecko.com/api/v3/coins/markets/market_chart?vs_currency=usd&per_page=50&page=1";
		
		//fetch data 
		RestTemplate restTemplate=  new RestTemplate();
		
		try {
			HttpHeaders headers=new HttpHeaders();
			
			HttpEntity<String> entity=new HttpEntity<String>("parameters",headers);
			
			ResponseEntity<String> response=restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
		
			return response.getBody();
			
		}catch (HttpClientErrorException | HttpServerErrorException e){
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String getTreadingCoins() throws Exception {
String url = "https://pro-api.coingecko.com/api/v3/search/treading";
		
		//fetch data 
		RestTemplate restTemplate=  new RestTemplate();
		
		try {
			HttpHeaders headers=new HttpHeaders();
			
			HttpEntity<String> entity=new HttpEntity<String>("parameters",headers);
			
			ResponseEntity<String> response=restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
		
			return response.getBody();
			
		}catch (HttpClientErrorException | HttpServerErrorException e){
			throw new Exception(e.getMessage());
		}
	}

}
