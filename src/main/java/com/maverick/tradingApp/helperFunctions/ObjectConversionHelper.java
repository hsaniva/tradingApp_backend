package com.maverick.tradingApp.helperFunctions;

import com.maverick.tradingApp.dto.TradeOrderDTO;
import com.maverick.tradingApp.model.TradeOrder;

public class ObjectConversionHelper {
    public static TradeOrderDTO BOToDTO(TradeOrder tradeOrder){
        return TradeOrderDTO
                .builder()
                .tradeOrderId(tradeOrder.getTradeOrderId())
                .stockStatusCode(tradeOrder.getStockStatusCode())
                .buyOrSell(tradeOrder.getBuyOrSell())
                .stockPrice(tradeOrder.getStockPrice())
                .stockVolume(tradeOrder.getStockVolume())
                .stockTickerLabel(tradeOrder.getStockTickerLabel())
                .build();
    }

    public static TradeOrder DTOToBO(TradeOrderDTO tradeOrderDTO){
        return TradeOrder
                .builder()
                .tradeOrderId(tradeOrderDTO.getTradeOrderId())
                .stockVolume(tradeOrderDTO.getStockVolume())
                .stockStatusCode(tradeOrderDTO.getStockStatusCode())
                .stockTickerLabel(tradeOrderDTO.getStockTickerLabel())
                .buyOrSell(tradeOrderDTO.getBuyOrSell())
                .stockPrice(tradeOrderDTO.getStockPrice())
                .build();
    }

}
