package com.example.lawassignment1backend;

import com.example.lawassignment1backend.repository.StockRepository;
import com.example.lawassignment1backend.service.StockService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer {
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockService stockService;

    @PostConstruct
    public void init() {
        stockService.updateStocks();
//        System.out.println("Repo content: " + stockRepository.findAll().toString());
        System.out.println("Initialization success!");
    }
}
