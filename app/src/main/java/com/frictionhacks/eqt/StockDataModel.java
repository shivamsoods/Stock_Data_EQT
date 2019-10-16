package com.frictionhacks.eqt;

public class StockDataModel {
    private String stockName, stockPrice, stockStatus;
    private String dayOpen, dayHigh, dayLow, lastTradedPrice;

    public StockDataModel() {

    }

    public StockDataModel(String stockName, String stockPrice, String stockStatus) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        this.stockStatus = stockStatus;
    }

    public StockDataModel(String stockName, String dayOpen, String dayHigh, String dayLow, String lastTradedPrice) {
        this.stockName = stockName;
        this.dayOpen = dayOpen;
        this.dayHigh = dayHigh;
        this.dayLow = dayLow;
        this.lastTradedPrice = lastTradedPrice;
    }

    public String getDayOpen() {
        return dayOpen;
    }

    public void setDayOpen(String dayOpen) {
        this.dayOpen = dayOpen;
    }

    public String getDayHigh() {
        return dayHigh;
    }

    public void setDayHigh(String dayHigh) {
        this.dayHigh = dayHigh;
    }

    public String getDayLow() {
        return dayLow;
    }

    public void setDayLow(String dayLow) {
        this.dayLow = dayLow;
    }

    public String getLastTradedPrice() {
        return lastTradedPrice;
    }

    public void setLastTradedPrice(String lastTradedPrice) {
        this.lastTradedPrice = lastTradedPrice;
    }


    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(String stockPrice) {
        this.stockPrice = stockPrice;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }
}
