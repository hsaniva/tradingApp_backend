package com.maverick.tradingApp.controller;

import com.maverick.tradingApp.dto.TradeOrderDTO;
import com.maverick.tradingApp.enums.StatusCode;
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
        tradeOrderService.createTradeOrder(tradeOrderDTO);
    }

    @DeleteMapping
    void deleteOrder(@RequestParam Integer tradeOrderId){
        tradeOrderService.deleteTradeOrderById(tradeOrderId);
    }

    @PutMapping
    void updateOrder(@RequestBody TradeOrderDTO tradeOrderDTO){
        //todo
    }

    @GetMapping("/{id}")
    public TradeOrderDTO getOrderById(@PathVariable(value = "id") Integer id){
        return tradeOrderService.getOrderById(id);
    }
    @GetMapping("/ticker/{tickerId}")
    List<TradeOrderDTO> getOrderByTickerId(@PathVariable(value = "tickerId") String tickerId){
        return tradeOrderService.getOrderByTickerId(tickerId);
    }

    @GetMapping("/statusCode/{statusCode}")
    List<TradeOrderDTO> getOrderByStatusCode(@PathVariable(value = "statusCode") String statusCode){
        return tradeOrderService.getOrderByStatusCode(StatusCode.valueOf(statusCode));
    }

}
