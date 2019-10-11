package com.frictionhacks.eqt;

public class StockDataModel {
    private String stockName,stockPrice,stockStatus;

    public StockDataModel(){

    }
    public StockDataModel(String stockName, String stockPrice, String stockStatus) {
       this.stockName=stockName;
       this.stockPrice=stockPrice;
       this.stockStatus=stockStatus;
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
