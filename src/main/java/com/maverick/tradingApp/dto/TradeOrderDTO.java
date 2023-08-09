package com.maverick.tradingApp.dto;

import com.maverick.tradingApp.enums.BuyOrSell;
import com.maverick.tradingApp.enums.StatusCode;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
}
