package com.maverick.tradingApp.service;

import com.maverick.tradingApp.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TradeOrderService {

    @Autowired
    private final OrderRepository orderRepository;



}
