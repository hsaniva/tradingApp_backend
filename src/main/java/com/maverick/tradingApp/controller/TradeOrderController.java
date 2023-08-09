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
    @ResponseStatus(HttpStatus.CREATED)
    void createOrder(@RequestBody TradeOrderDTO tradeOrderDTO){
        tradeOrderService.createTradeOrder(tradeOrderDTO);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    void deleteOrder(@RequestParam Integer tradeOrderId){
        tradeOrderService.deleteTradeOrderById(tradeOrderId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    void updateOrder(@RequestBody TradeOrderDTO tradeOrderDTO){
        tradeOrderService.updateTradeOrder(tradeOrderDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TradeOrderDTO getOrderById(@PathVariable(value = "id") Integer id){
        return tradeOrderService.getOrderById(id);
    }
    @GetMapping("/ticker/{tickerLabel}")
    @ResponseStatus(HttpStatus.OK)
    List<TradeOrderDTO> getOrderByTickerId(@PathVariable(value = "tickerLabel") String tickerLabel){
        return tradeOrderService.getOrderByTickerId(tickerLabel);
    }

    @GetMapping("/statusCode/{statusCode}")
    @ResponseStatus(HttpStatus.OK)
    List<TradeOrderDTO> getOrderByStatusCode(@PathVariable(value = "statusCode") String statusCode){
        return tradeOrderService.getOrderByStatusCode(StatusCode.valueOf(statusCode));
    }

}
