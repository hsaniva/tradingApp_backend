package com.maverick.tradingApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Portfolio {
    private UserDTO userDTO;
    private Long investedAmount;
    private Long presentAmount;
    private Double unrealizedProfitOrLoss;
    private List<HoldingDTO> holdings;
    private List<TradeOrderDTO> tradeOrderDTOs;
    private Integer stocksQuantity;
}
