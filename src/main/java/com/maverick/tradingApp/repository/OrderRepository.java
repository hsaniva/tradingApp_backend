package com.maverick.tradingApp.repository;

import com.maverick.tradingApp.enums.StatusCode;
import com.maverick.tradingApp.model.TradeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<TradeOrder, Integer> {
    List<TradeOrder> findByStockTickerLabel(String stockTickerLabel);
    List<TradeOrder> findByStockStatusCode(StatusCode statusCode);
}
