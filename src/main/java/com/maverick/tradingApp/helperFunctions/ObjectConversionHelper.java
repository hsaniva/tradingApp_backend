package com.maverick.tradingApp.helperFunctions;

/**
 * Avinash G, Karthik R, Priyanshy T
 */

import com.maverick.tradingApp.dto.TradeOrderDTO;
import com.maverick.tradingApp.model.TradeOrder;

public class ObjectConversionHelper {
    /**
     * Converts the Business object to the data transfer object
     * @param tradeOrder input BO
     * @return DTO object
     */
    public static TradeOrderDTO BOToDTO(TradeOrder tradeOrder){
        return TradeOrderDTO
                .builder()
                .tradeOrderId(tradeOrder.getTradeOrderId())
                .stockStatusCode(tradeOrder.getStockStatusCode())
                .buyOrSell(tradeOrder.getBuyOrSell())
                .stockPrice(tradeOrder.getStockPrice())
                .stockVolume(tradeOrder.getStockVolume())
                .stockTickerLabel(tradeOrder.getStockTickerLabel())
                .updatedOn(tradeOrder.getUpdatedOn())
                .createdOn(tradeOrder.getCreatedOn())
                .build();
    }

    /**
     * Converts the DTO to BO
     * @param tradeOrderDTO input DTO
     * @return BO
     */
    public static TradeOrder DTOToBO(TradeOrderDTO tradeOrderDTO){
        return TradeOrder
                .builder()
                .tradeOrderId(tradeOrderDTO.getTradeOrderId())
                .stockVolume(tradeOrderDTO.getStockVolume())
                .stockStatusCode(tradeOrderDTO.getStockStatusCode())
                .stockTickerLabel(tradeOrderDTO.getStockTickerLabel())
                .buyOrSell(tradeOrderDTO.getBuyOrSell())
                .stockPrice(tradeOrderDTO.getStockPrice())
                .updatedOn(tradeOrderDTO.getUpdatedOn())
                .createdOn(tradeOrderDTO.getCreatedOn())
                .build();
    }

}
