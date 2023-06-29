package com.example.lawassignment1backend.repository;

import com.example.lawassignment1backend.model.Stock;

import java.util.List;

public interface StockRepository {
    List<Stock> findAll();
    Stock findBySymbol(String symbol);
    int size();
    void add(Stock stock);
}
