package com.maverick.tradingApp.service;

import com.maverick.tradingApp.dto.TradeOrderDTO;
import com.maverick.tradingApp.enums.BuyOrSell;
import com.maverick.tradingApp.enums.StatusCode;
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
        return orderRepository.findAll().stream()
                .map(tradeOrder -> TradeOrderDTO
                        .builder()
                        .tradeOrderId(tradeOrder.getTradeOrderId())
                        .buyOrSell(tradeOrder.getBuyOrSell())
                        .stockPrice(tradeOrder.getStockPrice())
                        .stockStatusCode(tradeOrder.getStockStatusCode())
                        .stockVolume(tradeOrder.getStockVolume())
                        .stockTickerLabel(tradeOrder.getStockTickerLabel())
                        .build())
                .toList();
    }

    public void deleteTradeOrderById(int tradeOrderId){
        orderRepository.deleteById(tradeOrderId);
    }

    public void createTradeOrder(TradeOrderDTO tradeOrderDTO){
        orderRepository.save(TradeOrder
                .builder()
                .tradeOrderId(tradeOrderDTO.getTradeOrderId())
                .stockVolume(tradeOrderDTO.getStockVolume())
                .stockStatusCode(tradeOrderDTO.getStockStatusCode())
                .stockTickerLabel(tradeOrderDTO.getStockTickerLabel())
                .buyOrSell(tradeOrderDTO.getBuyOrSell())
                .stockPrice(tradeOrderDTO.getStockPrice())
                .build());
    }

    public TradeOrderDTO getOrderById(Integer id) {
        TradeOrder tradeOrder = orderRepository.findById(id).get();
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

    public List<TradeOrderDTO> getOrderByTickerId(String tickerId) {
        List<TradeOrder> tradeOrders = orderRepository.findByStockTickerLabel(tickerId);
        return tradeOrders.stream().map(tradeOrder ->
                TradeOrderDTO
                        .builder()
                        .tradeOrderId(tradeOrder.getTradeOrderId())
                        .stockStatusCode(tradeOrder.getStockStatusCode())
                        .buyOrSell(tradeOrder.getBuyOrSell())
                        .stockPrice(tradeOrder.getStockPrice())
                        .stockVolume(tradeOrder.getStockVolume())
                        .stockTickerLabel(tradeOrder.getStockTickerLabel())
                        .build()
                ).toList();
    }

    public List<TradeOrderDTO> getOrderByStatusCode(StatusCode statusCode) {
        List<TradeOrder> tradeOrders = orderRepository.findByStockStatusCode(statusCode);
        return tradeOrders.stream().map(tradeOrder ->
                TradeOrderDTO
                        .builder()
                        .tradeOrderId(tradeOrder.getTradeOrderId())
                        .stockStatusCode(tradeOrder.getStockStatusCode())
                        .buyOrSell(tradeOrder.getBuyOrSell())
                        .stockPrice(tradeOrder.getStockPrice())
                        .stockVolume(tradeOrder.getStockVolume())
                        .stockTickerLabel(tradeOrder.getStockTickerLabel())
                        .build()
        ).toList();

    }
}
