package com.maverick.tradingApp.controller;

import com.maverick.tradingApp.dto.TradeOrderDTO;
import com.maverick.tradingApp.service.TradeOrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class TradeOrderController {

    @Autowired
    private final TradeOrderService tradeOrderService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TradeOrderDTO> getAllOrders(){
        return tradeOrderService.getAllOrders();
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
