package com.maverick.tradingApp.controller;

import com.maverick.tradingApp.dto.TradeOrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class TradeOrderController {

    @GetMapping
    void getAllOrders(){
        //todo
    }

    @PostMapping
    void createOrder(@RequestBody TradeOrderDTO tradeOrderDTO){
        //todo
    }

    @DeleteMapping
    void deleteOrder(@RequestParam Integer tradeOrderId){
        //todo
    }

    @PutMapping
    void updateOrder(@RequestBody TradeOrderDTO tradeOrderDTO){
        //todo
    }

    @GetMapping("/{id}")
    void getOrderById(@PathVariable Integer tradeOrderId){
        //todo
    }
    @GetMapping("/{tickerId}")
    void getOrderByTickerId(@PathVariable Integer tradeOrderTickerId){
        //todo
    }

    @GetMapping("/{statusCode}")
    void getOrderByStatusCode(@PathVariable Integer statusCode){
        //todo
    }

}
