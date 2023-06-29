package com.example.lawassignment1backend.service;

import com.example.lawassignment1backend.model.StockTracker;
import com.example.lawassignment1backend.model.StockTrackerReport;
import com.example.lawassignment1backend.repository.StockRepository;
import com.example.lawassignment1backend.repository.StockTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class StockTrackerServiceImpl implements StockTrackerService{

    @Autowired
    StockTrackerRepository stockTrackerRepository;

    @Autowired
    StockService stockService;

    @Autowired
    StockRepository stockRepository;

    @Override
    public StockTracker saveStockTracker(StockTracker stockTracker) {
        if (stockRepository.findAll().stream().anyMatch((stock) -> stockTracker.getSymbol().equals(stock.getSymbol()))){
            Long closedPrice = stockService.updateClosedPrice(stockTracker.getSymbol());
            stockTracker.setDate(new Date());
            stockTracker.setClosedPrice(closedPrice);
            return stockTrackerRepository.save(stockTracker);
        }
        return null;
    }

    @Override
    public void deleteStockTracker(String id) {
        stockTrackerRepository.deleteById(id);
    }

    @Override
    @Scheduled(cron = "0 0 18 * * ?", zone = "Asia/Jakarta")
    public void updateStockClosedPrice() {
        stockTrackerRepository.findAll().forEach((stock) -> stockService.updateClosedPrice(stock.getSymbol()));
    }

    @Override
    public ArrayList<StockTrackerReport> generateReport() {
        ArrayList<StockTrackerReport> reports = new ArrayList<>();
        stockTrackerRepository.findAll().forEach((stockTracker ->
                reports.add(new StockTrackerReport(stockTracker))));
        return reports;
    }


}
