package com.maverick.tradingApp.service;

import com.maverick.tradingApp.dto.TradeOrderDTO;
import com.maverick.tradingApp.model.TradeOrder;
import com.maverick.tradingApp.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TradeOrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public List<TradeOrderDTO> getAllOrders(){
        return orderRepository.findAll().stream()
                .map(tradeOrder -> TradeOrderDTO
                        .builder()
                        .tradeOrderId(tradeOrder.getTradeOrderId())
                        .buyOrSell(tradeOrder.getBuyOrSell())
                        .stockPrice(tradeOrder.getStockPrice())
                        .stockTickerLabel(tradeOrder.getStockTickerLabel())
                        .stockVolume(tradeOrder.getStockVolume())
                        .build())
                .toList();
    }

}
