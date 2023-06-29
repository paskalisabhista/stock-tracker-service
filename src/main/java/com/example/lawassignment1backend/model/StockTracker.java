package com.example.lawassignment1backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "stock_tracker")
@NoArgsConstructor
public class StockTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "lot")
    private int lot;

    @Column(name = "average_price")
    private double averagePrice;

    @Column(name = "closed_price")
    private Long closedPrice;

    @Column(name = "profit_target_percentage")
    private int profitTarget;

    @Column(name = "date_added")
    private Date date;

    public StockTracker(String symbol, int lot, int averagePrice, int profitTarget) {
        this.symbol = symbol;
        this.lot = lot;
        this.averagePrice = averagePrice;
        this.profitTarget = profitTarget;
        this.closedPrice = 0L;
        this.date = new Date();
    }
}
