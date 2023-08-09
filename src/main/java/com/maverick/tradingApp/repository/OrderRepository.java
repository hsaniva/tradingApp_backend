package com.maverick.tradingApp.repository;

/**
 * Avinash G, Karthik R, Priyanshy T
 */
import com.maverick.tradingApp.enums.StatusCode;
import com.maverick.tradingApp.model.TradeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<TradeOrder, Integer> {
    /**
     *
     * @param stockTickerLabel input ticker label param for filtering.
     * @return Returns a list of tradeOrders where tickerLabel matches with the @param
     */
    List<TradeOrder> findByStockTickerLabel(String stockTickerLabel);

    /**
     *
     * @param statusCode input status code
     * @return list of trade orders based on the input status code.
     */
    List<TradeOrder> findByStockStatusCode(StatusCode statusCode);
}
