package com.maverick.tradingApp.repository;

import com.maverick.tradingApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for User.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
