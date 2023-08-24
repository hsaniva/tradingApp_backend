package com.maverick.tradingApp.repository;
/**
 * Avinash G, Karthik R, Priyanshu T
 */

import com.maverick.tradingApp.model.Holding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoldingRepository extends JpaRepository<Holding, String> {
    List<Holding> findByUserId(String userId);
}
