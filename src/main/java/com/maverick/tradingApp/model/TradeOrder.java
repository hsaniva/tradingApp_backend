package com.maverick.tradingApp.model;

import com.maverick.tradingApp.enums.BuyOrSell;
import com.maverick.tradingApp.enums.StatusCode;
import jakarta.persistence.*;
import lombok.*;


/**
 * @author Avinash G, Karthik R, Priyanshu T
 *
 *
 */
@Entity
@Table(name = "order_table")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TradeOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tradeOrderId; // Auto generated Trade Order ID
    String stockTickerLabel; // Ticker Label like 'AMZN'
    double stockPrice; // Stock price at which the trade needs to take place
    int stockVolume; // Number of stocks
    BuyOrSell buyOrSell; // takes value BUY OR SELL
    StatusCode stockStatusCode; // takes values PENDING or EXECUTED or REJECTED
}
