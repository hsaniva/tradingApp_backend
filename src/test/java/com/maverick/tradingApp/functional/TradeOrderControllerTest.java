package com.maverick.tradingApp.functional;

import com.maverick.tradingApp.controller.TradeOrderController;
import com.maverick.tradingApp.dto.TradeOrderDTO;
import com.maverick.tradingApp.enums.BuyOrSell;
import com.maverick.tradingApp.enums.StatusCode;
import com.maverick.tradingApp.helperFunctions.ObjectConversionHelper;
import com.maverick.tradingApp.model.TradeOrder;
import com.maverick.tradingApp.service.TradeOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TradeOrderControllerTest {

    @Autowired
    WebTestClient webTestClient;


    TradeOrderService tradeOrderService = mock(TradeOrderService.class);

    @InjectMocks
    private TradeOrderController tradeOrderController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAllOrdersTest_shouldReturnAllOrders() {
        List<TradeOrder> mockOrders  = new ArrayList<>();
        mockOrders.add(
                new TradeOrder("1", "AAPL",200.0,
                        200, BuyOrSell.BUY, StatusCode.PENDING,
                        new Timestamp(1000),
                        new Timestamp(System.currentTimeMillis()),
                        "102339","Apple"));
        when(tradeOrderService.getAllOrders()).thenReturn(mockOrders.stream()
                .map(ObjectConversionHelper::BOToDTO)
                .toList());

        List<TradeOrderDTO> result = tradeOrderService.getAllOrders();

        assertEquals(mockOrders, result);

        webTestClient.get()
                .uri("/api/order")
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectBodyList(TradeOrderDTO.class)
                .isEqualTo(mockOrders.stream()
                        .map(ObjectConversionHelper::BOToDTO)
                        .toList());
    }

    @Test
    void createOrder_shouldCreateNewOrder() {

//
//        webTestClient.post()
//                .uri("/api/order")
//                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
//                .exchange()
//                .expectStatus().isCreated();
    }



    @Test
    void updateOrder_shouldUpdateOrder() {
        webTestClient.put()
                .uri("/api/order")
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isOk();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/OrderIdTC.csv")
    void getOrderById(int id) {
        webTestClient.get()
                .uri("/api/order/{id}", id)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isOk();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/TickerIdTC.csv")
    void getOrderByTickerId(int id) {
        webTestClient.get()
                .uri("/api/order/ticker/{tickerLabel}", id)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isOk();
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/StatusCodeTC.csv")
    void getOrderByStatusCode(int code) {
        webTestClient.get()
                .uri("/api/order/statusCode/{id}", code)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isOk();
    }
}