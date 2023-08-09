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
@Table(name = "order")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orderId;
    String stockTicker;
    double price;
    int volume;
    int buyOrSell;
    int statusCode;
}
