package com.maverick.tradingApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maverick.tradingApp.model.Holding;
import lombok.*;

import java.util.List;
import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Portfolio {
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class StockInfo{
        private Double buyValue;
        private Double presentValue;
        private int quantity;
    }
    private UserDTO userDTO;
    private Long investedAmount;
    private Long presentAmount;
    private Double unrealizedProfitOrLoss;
    private List<Holding> holdings;
    private Map<String, StockInfo> stockHoldingRatios;
    private List<TradeOrderDTO> tradeOrderDTOs;
    private Integer stocksQuantity;
}
