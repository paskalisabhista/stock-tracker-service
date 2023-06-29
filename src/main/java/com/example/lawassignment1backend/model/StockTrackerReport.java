package com.example.lawassignment1backend.model;

import lombok.Data;

@Data
public class StockTrackerReport {
    private String stockSymbol;
    private double averagePrice;
    private Long closedPrice;
    private double stockPriceChange;
    private double gainOrLossAmount;
    private String stockChangePercentage;
    private String profitTarget;

    public StockTrackerReport(StockTracker stockTracker){
        this.stockSymbol = stockTracker.getSymbol();
        this.averagePrice = stockTracker.getAveragePrice();
        this.closedPrice = stockTracker.getClosedPrice();
        this.stockPriceChange = stockTracker.getAveragePrice() - stockTracker.getClosedPrice();
        this.gainOrLossAmount =  (stockTracker.getClosedPrice() - stockTracker.getAveragePrice()) * (stockTracker.getLot() * 100);
        this.stockChangePercentage =  String.format("%.2f", (this.getGainOrLossAmount() / (stockTracker.getAveragePrice() * (stockTracker.getLot() * 100))) * 100) + "%";
        this.profitTarget = stockTracker.getProfitTarget() + "%";
    }
}
