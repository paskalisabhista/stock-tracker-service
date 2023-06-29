package com.example.lawassignment1backend.repository;

import com.example.lawassignment1backend.model.Stock;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StockRepositoryImpl implements StockRepository{
    private final List<Stock> stockList = new ArrayList<>();

    @Override
    public List<Stock> findAll() {
        return stockList;
    }

    @Override
    public Stock findBySymbol(String symbol) {
        for (Stock stock : stockList){
            if (stock.getSymbol().equals(symbol)){
                return stock;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return stockList.size();
    }

    @Override
    public void add(Stock stock) {
        stockList.add(stock);
    }
}
