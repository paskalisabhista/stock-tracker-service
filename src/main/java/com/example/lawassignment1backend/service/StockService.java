package com.example.lawassignment1backend.service;

public interface StockService {
    void updateStocks();
    Long updateClosedPrice(String symbol);
}
