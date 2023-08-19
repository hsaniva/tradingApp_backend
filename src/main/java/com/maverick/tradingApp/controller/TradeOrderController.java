package com.maverick.tradingApp.controller;

import com.maverick.tradingApp.dto.TradeOrderDTO;
import com.maverick.tradingApp.enums.StatusCode;
import com.maverick.tradingApp.service.TradeOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling orders.
 */
@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
@CrossOrigin
public class TradeOrderController {

    private final TradeOrderService tradeOrderService;

    /**
     * getAllOrders function is called when there is a GET request to`/api/order`
     * @return List<TradeOrderDTO> the list of orders
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TradeOrderDTO> getAllOrders(){
        return tradeOrderService.getAllOrders();
    }

    /**
     * createOrder function is called when there is a POST request to `/api/order`
     * @param tradeOrderDTO a instance of the new trade order
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createOrder(@RequestBody TradeOrderDTO tradeOrderDTO){
        tradeOrderService.createTradeOrder(tradeOrderDTO);
    }

    /**
     * deleteOrder is called when there is a DELETE request to `/api/order?id={tradeOrderId}`
     * @param tradeOrderId an integer value denoting the value of the order id.
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    void deleteOrder(@RequestParam Integer tradeOrderId){
        tradeOrderService.deleteTradeOrderById(tradeOrderId);
    }

    /**
     * updateOrder is called when there is a PUT request to `/api/order`
     * @param tradeOrderDTO an object of TradeOrderDTO type denoting containing the new values
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    void updateOrder(@RequestBody TradeOrderDTO tradeOrderDTO){
        tradeOrderService.updateTradeOrder(tradeOrderDTO);
    }

    /**
     * getOrderById is called when there is a GET request to `/api/order/{id}`
     * @param id an integer denoting the value of the Order which is being searched.
     * @return an object of TradeOrderDTO type
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TradeOrderDTO getOrderById(@PathVariable(value = "id") Integer id){
        return tradeOrderService.getOrderById(id);
    }

    /**
     * getOrderByTickerId is called when there is a GET request to `/api/order/ticker/{tickerLable}`
     * @param tickerLabel an integer denoting the value of the ticker which is being queried.
     * @return an List of TradeOrderDTO object
     */
    @GetMapping("/ticker/{tickerLabel}")
    @ResponseStatus(HttpStatus.OK)
    List<TradeOrderDTO> getOrderByTickerId(@PathVariable(value = "tickerLabel") String tickerLabel){
        return tradeOrderService.getOrderByTickerId(tickerLabel);
    }

    /**
     * getOrderByStatusCode is called when there is a GET request to `/api/order/statusCode/{statusCode}`
     * @param statusCode an integer denoting the status code for which orders are being searched.
     * @return List of Orders matching the {statusCode}
     */
    @GetMapping("/statusCode/{statusCode}")
    @ResponseStatus(HttpStatus.OK)
    List<TradeOrderDTO> getOrderByStatusCode(@PathVariable(value = "statusCode") String statusCode){
        return tradeOrderService.getOrderByStatusCode(StatusCode.valueOf(statusCode));
    }

    /**
     * Get all orders other than the cancelled ones.
     * @return List of orders.
     */
    @GetMapping("/valid")
    @ResponseStatus(HttpStatus.OK)
    List<TradeOrderDTO> getValidOrderList(){
        return tradeOrderService.findByStatusCodeNot(StatusCode.CANCELLED);
    }

    /**
     * Just cancels the order.
     * @param tradeOrderId
     */
    @PutMapping("/cancel")
    @ResponseStatus(HttpStatus.OK)
    public void cancelOrder(@RequestParam Integer tradeOrderId){
        tradeOrderService.cancelOrder(tradeOrderId);
    }

    /**
     * Returns all the orders related to a particular user.
     * @param userID user id
     * @return list of orders related to the user.
     */
    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    List<TradeOrderDTO> getOrderByUserId(@PathVariable(value = "userId") String userID){
        return tradeOrderService.getOrderByUserId(userID);
    }
//    user info apis
//
//    get all tickers label
//    get profit details
//
//    get profit/loss ticket wise





}
