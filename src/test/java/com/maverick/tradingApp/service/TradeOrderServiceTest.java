package com.maverick.tradingApp.service;

import com.maverick.tradingApp.dto.TradeOrderDTO;
import com.maverick.tradingApp.enums.BuyOrSell;
import com.maverick.tradingApp.enums.StatusCode;
import com.maverick.tradingApp.helperFunctions.ObjectConversionHelper;
import com.maverick.tradingApp.model.TradeOrder;
import com.maverick.tradingApp.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


@SpringBootTest(classes=TradeOrderService.class)
class TradeOrderServiceTest {

    @Autowired
    TradeOrderService tradeOrderService;

    @MockBean
    OrderRepository mockRepo =  mock(OrderRepository.class);;

    ObjectConversionHelper objectConversionHelper;



    TradeOrder dummyOrder;

    TradeOrderDTO dummyDTOOrder;
    @BeforeEach
    void setUp() {
        dummyOrder = new TradeOrder("1", "AAPL",200.0,
                200, BuyOrSell.BUY, StatusCode.PENDING,
                new Timestamp(1000),
                new Timestamp(System.currentTimeMillis()),
                "1","Apple");

        dummyDTOOrder = objectConversionHelper.BOToDTO(dummyOrder);


    }

    @Test
    void getAllOrders() {
    }

    @Test
    void deleteTradeOrderById() {
    }

    @Test
    void createTradeOrder() {
    }

    @Test
    void getOrderById() {
        given(mockRepo.findById("1")).willReturn(Optional.of(dummyOrder));
        TradeOrderDTO result = tradeOrderService.getOrderById("1");
        assertThat(result.equals(dummyDTOOrder));
    }

    @Test
    void getOrderByTickerId() {
    }

    @Test
    void getOrderByStatusCode() {
    }

    @Test
    void updateTradeOrder() {
    }

    @Test
    void findByStatusCodeNot() {
    }

    @Test
    void cancelOrder() {
    }

    @Test
    void getOrderByUserId() {
    }
}