package com.maverick.tradingApp.functional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.junit.jupiter.params.provider.CsvFileSource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TradeOrderControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void getAllOrdersTest_shouldReturnAllOrders() {
        webTestClient.get()
                .uri("/api/order")
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void createOrder_shouldCreateNewOrder() {
        webTestClient.post()
                .uri("/api/order")
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void deleteOrder_shoudDelete(int id) {
        webTestClient.delete()
                .uri("/api/order", id)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isOk();
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