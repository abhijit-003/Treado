package com.codex.modal;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class Coin {

    @Id
    @JsonProperty("id")
    private String id; // e.g., "bitcoin"

    @JsonProperty("symbol")
    private String symbol; // e.g., "btc"

    @JsonProperty("name")
    private String name; // e.g., "Bitcoin"

    @JsonProperty("image")
    private String image; // e.g., URL to the image

    @JsonProperty("current_price")
    private double currentPrice; // e.g., 70187

    @JsonProperty("market_cap")
    private long marketCap; // e.g., 1381651251183

    @JsonProperty("market_cap_rank")
    private int marketCapRank; // e.g., 1

    @JsonProperty("fully_diluted_valuation")
    private long fullyDilutedValuation; // e.g., 1474623675796

    @JsonProperty("total_volume")
    private long totalVolume; // e.g., 20154184933

    @JsonProperty("high_24h")
    private double high24h; // e.g., 70215

    @JsonProperty("low_24h")
    private double low24h; // e.g., 68060

    @JsonProperty("price_change_24h")
    private double priceChange24h; // e.g., 2126.88

    @JsonProperty("price_change_percentage_24h")
    private double priceChangePercentage24h; // e.g., 3.12502

    @JsonProperty("market_cap_change_24h")
    private long marketCapChange24h; // e.g., 44287678051

    @JsonProperty("market_cap_change_percentage_24h")
    private double marketCapChangePercentage24h; // e.g., 3.31157

    @JsonProperty("circulating_supply")
    private double circulatingSupply; // e.g., 19675987

    @JsonProperty("total_supply")
    private double totalSupply; // e.g., 21000000

    @JsonProperty("max_supply")
    private double maxSupply; // e.g., 21000000

    @JsonProperty("ath")
    private double ath; // All-time high, e.g., 73738

    @JsonProperty("ath_change_percentage")
    private double athChangePercentage; // e.g., -4.77063

    @JsonProperty("ath_date")
    private LocalDateTime athDate; // e.g., "2024-03-14T07:10:36.635Z"

    @JsonProperty("atl")
    private double atl; // All-time low, e.g., 67.81

    @JsonProperty("atl_change_percentage")
    private double atlChangePercentage; // e.g., 103455.83335

    @JsonProperty("atl_date")
    private LocalDateTime atlDate; // e.g., "2013-07-06T00:00:00.000Z"

    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated; // e.g., "2024-04-07T16:49:31.736Z"

    @JsonProperty("roi")
    @JsonIgnore
    private Double roi; // ROI can be null, so using Double

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public long getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(long marketCap) {
		this.marketCap = marketCap;
	}

	public int getMarketCapRank() {
		return marketCapRank;
	}

	public void setMarketCapRank(int marketCapRank) {
		this.marketCapRank = marketCapRank;
	}

	public long getFullyDilutedValuation() {
		return fullyDilutedValuation;
	}

	public void setFullyDilutedValuation(long fullyDilutedValuation) {
		this.fullyDilutedValuation = fullyDilutedValuation;
	}

	public long getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(long totalVolume) {
		this.totalVolume = totalVolume;
	}

	public double getHigh24h() {
		return high24h;
	}

	public void setHigh24h(double high24h) {
		this.high24h = high24h;
	}

	public double getLow24h() {
		return low24h;
	}

	public void setLow24h(double low24h) {
		this.low24h = low24h;
	}

	public double getPriceChange24h() {
		return priceChange24h;
	}

	public void setPriceChange24h(double priceChange24h) {
		this.priceChange24h = priceChange24h;
	}

	public double getPriceChangePercentage24h() {
		return priceChangePercentage24h;
	}

	public void setPriceChangePercentage24h(double priceChangePercentage24h) {
		this.priceChangePercentage24h = priceChangePercentage24h;
	}

	public long getMarketCapChange24h() {
		return marketCapChange24h;
	}

	public void setMarketCapChange24h(long marketCapChange24h) {
		this.marketCapChange24h = marketCapChange24h;
	}

	public double getMarketCapChangePercentage24h() {
		return marketCapChangePercentage24h;
	}

	public void setMarketCapChangePercentage24h(double marketCapChangePercentage24h) {
		this.marketCapChangePercentage24h = marketCapChangePercentage24h;
	}

	public double getCirculatingSupply() {
		return circulatingSupply;
	}

	public void setCirculatingSupply(double circulatingSupply) {
		this.circulatingSupply = circulatingSupply;
	}

	public double getTotalSupply() {
		return totalSupply;
	}

	public void setTotalSupply(double totalSupply) {
		this.totalSupply = totalSupply;
	}

	public double getMaxSupply() {
		return maxSupply;
	}

	public void setMaxSupply(double maxSupply) {
		this.maxSupply = maxSupply;
	}

	public double getAth() {
		return ath;
	}

	public void setAth(double ath) {
		this.ath = ath;
	}

	public double getAthChangePercentage() {
		return athChangePercentage;
	}

	public void setAthChangePercentage(double athChangePercentage) {
		this.athChangePercentage = athChangePercentage;
	}

	public LocalDateTime getAthDate() {
		return athDate;
	}

	public void setAthDate(LocalDateTime athDate) {
		this.athDate = athDate;
	}

	public double getAtl() {
		return atl;
	}

	public void setAtl(double atl) {
		this.atl = atl;
	}

	public double getAtlChangePercentage() {
		return atlChangePercentage;
	}

	public void setAtlChangePercentage(double atlChangePercentage) {
		this.atlChangePercentage = atlChangePercentage;
	}

	public LocalDateTime getAtlDate() {
		return atlDate;
	}

	public void setAtlDate(LocalDateTime atlDate) {
		this.atlDate = atlDate;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}

    
}
