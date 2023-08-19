package com.maverick.tradingApp.helperFunctions;

/**
 * Avinash G, Karthik R, Priyanshy T
 */

import com.maverick.tradingApp.dto.TradeOrderDTO;
import com.maverick.tradingApp.dto.UserDTO;
import com.maverick.tradingApp.model.TradeOrder;
import com.maverick.tradingApp.model.User;

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
                .userId(tradeOrder.getUserId())
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
                .userId(tradeOrderDTO.getUserId())
                .build();
    }
    /**
     * Converts the BO to DTO
     * @param user input BO
     * @return DTO
     */
    public static UserDTO BOToDTO(User user){
        return UserDTO.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .bankAccount(user.getBankAccount())
                .build();
    }
    /**
     * Converts the DTO to BO
     * @param userDTO input DTO
     * @return BO
     */
    public static User DTOToBO(UserDTO userDTO){
        return User.builder()
                .userId(userDTO.getUserId())
                .name(userDTO.getName())
                .bankAccount(userDTO.getBankAccount())
                .build();
    }

}
