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
    private int tradeOrderId;
    String stockTickerLabel;
    double stockPrice;
    int stockVolume;
    BuyOrSell buyOrSell;
    StatusCode stockStatusCode;
}
