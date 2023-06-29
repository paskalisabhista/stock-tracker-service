package com.example.lawassignment1backend.service;

import com.example.lawassignment1backend.model.StockTracker;
import com.example.lawassignment1backend.model.StockTrackerReport;

import java.util.ArrayList;

public interface StockTrackerService {
    StockTracker saveStockTracker(StockTracker stockTracker);
    void deleteStockTracker(String id);
    void updateStockClosedPrice();
    ArrayList<StockTrackerReport> generateReport();
}
