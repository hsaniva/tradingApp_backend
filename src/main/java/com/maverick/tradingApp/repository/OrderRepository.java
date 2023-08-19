package com.maverick.tradingApp.repository;

/**
 * Avinash G, Karthik R, Priyanshy T
 */

import com.maverick.tradingApp.enums.StatusCode;
import com.maverick.tradingApp.model.TradeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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

    /**
     * Find by except status code
     * @param statusCode which shouldn't be included in the resultset.
     * @return list of tradeorders.
     */
    List<TradeOrder> findByStockStatusCodeNot(StatusCode statusCode);

    /**
     * Find orders by user id
     * @param userId
     * @return list of trades.
     */
    List<TradeOrder> findByUserId(String userId);
}
