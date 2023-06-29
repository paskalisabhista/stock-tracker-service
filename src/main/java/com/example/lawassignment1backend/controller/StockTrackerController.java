package com.example.lawassignment1backend.controller;

import com.example.lawassignment1backend.model.StockTracker;
import com.example.lawassignment1backend.model.StockTrackerReport;
import com.example.lawassignment1backend.service.StockTrackerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class StockTrackerController {

    @Autowired
    StockTrackerService stockTrackerService;

    @PostMapping("add-tracker")
    public ResponseEntity<StockTracker> createStockTracker(@RequestBody StockTracker stockTracker){
        StockTracker newStockTracker = stockTrackerService.saveStockTracker(stockTracker);
        return (newStockTracker != null)? new ResponseEntity<>(newStockTracker, HttpStatus.CREATED): ResponseEntity.badRequest().body(null);
    }

    @GetMapping("report")
    public ResponseEntity<ArrayList<StockTrackerReport>> getStockReport(){
        ArrayList<StockTrackerReport> reports = stockTrackerService.generateReport();
        return (reports != null)? new ResponseEntity<>(reports, HttpStatus.CREATED): ResponseEntity.badRequest().body(null);
    }
}
