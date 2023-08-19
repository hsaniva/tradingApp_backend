package com.maverick.tradingApp.repository;

/**
 * @author Avinash G, Karthik R, Priyanshu T
 */

import com.maverick.tradingApp.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for Bank account
 */
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
