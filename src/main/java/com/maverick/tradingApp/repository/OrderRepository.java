package com.maverick.tradingApp.repository;

import com.maverick.tradingApp.model.TradeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<TradeOrder, Integer> {

}
