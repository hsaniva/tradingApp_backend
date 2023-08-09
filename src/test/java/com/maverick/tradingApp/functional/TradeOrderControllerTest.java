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
    void deleteOrder() {
        webTestClient.delete()
                .uri("/api/order")
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    void updateOrder() {
        webTestClient.put()
                .uri("/api/order")
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/OrderIdTC.csv")
    void getOrderById(int id) {
        webTestClient.get()
                .uri("/api/order/?orderId={id}", id)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/TickerIdTC.csv")
    void getOrderByTickerId(int id) {
        webTestClient.get()
                .uri("/api/order/?ticketId={id}", id)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/TickerIdTC.csv")
    void getOrderByStatusCode(int code) {
        webTestClient.get()
                .uri("/api/order/?statusCode={id}", code)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }
}