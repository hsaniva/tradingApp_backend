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

import java.util.Date;

/**
 * Data transfer object class
 * This object will be used to transfer information to the backend
 */
@Data
@Builder
@Getter
@Setter
public class TradeOrderDTO {
    private int tradeOrderId;
    String stockTickerLabel;
    double stockPrice;
    int stockVolume;
    BuyOrSell buyOrSell;
    StatusCode stockStatusCode;

    Date createdOn;  // created on date

    Date updatedOn; // date when the trade order was updated
}
