package com.maverick.tradingApp.service;

import com.maverick.tradingApp.dto.TradeOrderDTO;
import com.maverick.tradingApp.enums.BuyOrSell;
import com.maverick.tradingApp.enums.StatusCode;
import com.maverick.tradingApp.helperFunctions.ObjectConversionHelper;
import com.maverick.tradingApp.model.TradeOrder;
import com.maverick.tradingApp.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TradeOrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public List<TradeOrderDTO> getAllOrders(){
        /**
         * returns list of Trade orders in DTO format
         */
        return orderRepository.findAll().stream()
                .map(ObjectConversionHelper::BOToDTO)
                .toList();
    }

    public void deleteTradeOrderById(int tradeOrderId){
        /**
         * tradeOrderId:: trade order ID
         *
         */
        orderRepository.deleteById(tradeOrderId);
    }

    public void createTradeOrder(TradeOrderDTO tradeOrderDTO){
        /**
         * tradeOrderDTO:: trade Order DTO
         */

        orderRepository.save(ObjectConversionHelper.DTOToBO(tradeOrderDTO));
    }

    public TradeOrderDTO getOrderById(Integer id) {
        /**
         * id:: id of the order needed
         * returns the id selected Trade order DTO
         */
        TradeOrder tradeOrder = orderRepository.findById(id).get();
        return ObjectConversionHelper.BOToDTO(tradeOrder);
    }

    public List<TradeOrderDTO> getOrderByTickerId(String tickerId) {
        /**
         * tickerId:: Ticker Id like 'AMZN' or 'AAPL'
         * returns the list of Trade order DTP filtered by Ticker ID
         */
        List<TradeOrder> tradeOrders = orderRepository.findByStockTickerLabel(tickerId);
        return tradeOrders.stream().map(ObjectConversionHelper::BOToDTO).toList();
    }

    public List<TradeOrderDTO> getOrderByStatusCode(StatusCode statusCode) {
        /**
         * statusCode:: Enum can be PENDING
         * returns the Trade Order DTO filtered by status code
         */
        List<TradeOrder> tradeOrders = orderRepository.findByStockStatusCode(statusCode);
        return tradeOrders.stream().map(ObjectConversionHelper::BOToDTO).toList();

    }

    public void updateTradeOrder(TradeOrderDTO tradeOrderDTO) {
        /**
         * tradeOrderDTO:: updated TradeOrder DTO
         */
        orderRepository.save(ObjectConversionHelper.DTOToBO(tradeOrderDTO));
    }
}
