package com.maverick.tradingApp.dto;
/**
 * @authors Avinash G, Priyanshu T, Karthik R
 */

import com.maverick.tradingApp.enums.BuyOrSell;
import com.maverick.tradingApp.enums.StatusCode;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.sql.Timestamp;


/**
 * Data transfer object class
 * This object will be used to transfer information to the backend
 */
@Data
@Builder
@Getter
@Setter
public class TradeOrderDTO {
    private String tradeOrderId;
    String stockTickerLabel;
    double stockPrice;
    int stockVolume;
    BuyOrSell buyOrSell;
    StatusCode stockStatusCode;
    Timestamp createdOn;
    Timestamp updatedOn;
    String userId;

    String stockName;
}
