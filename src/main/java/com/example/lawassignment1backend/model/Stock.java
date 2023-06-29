package com.example.lawassignment1backend.model;

import lombok.Data;

import java.util.Date;

@Data
public class Stock {
    private String symbol;
    private String companyName;
    private Long closedPrice;
    private Date lastUpdate;

    public Stock(String symbol, String companyName) {
        Date today = new Date(); // Current date
        Date yesterday = new Date(today.getTime() - 86400000L); // Subtract one day in milliseconds

        this.symbol = symbol;
        this.companyName = companyName;
        this.closedPrice = 0L;
        this.lastUpdate = yesterday;
    }
}
