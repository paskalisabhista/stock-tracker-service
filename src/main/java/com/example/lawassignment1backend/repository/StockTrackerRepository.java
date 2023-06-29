package com.example.lawassignment1backend.repository;

import com.example.lawassignment1backend.model.StockTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockTrackerRepository extends JpaRepository<StockTracker, String> {
}
