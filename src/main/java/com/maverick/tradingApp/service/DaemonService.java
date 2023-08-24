package com.maverick.tradingApp.service;

import com.maverick.tradingApp.dto.TradeOrderDTO;
import com.maverick.tradingApp.enums.BuyOrSell;
import com.maverick.tradingApp.enums.StatusCode;
import com.maverick.tradingApp.model.Holding;
import com.maverick.tradingApp.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DaemonService {

    private final TradeOrderService tradeOrderService;
    private final StockMarketService stockMarketService;
    private final UserService userService;

    private final HoldingService holdingService;

    /**
     * Daemon function that runs every 30 seconds and checks if any PENDING trade orders can be executed
     * This Daemon is active only between 9am and 3:30pm
     */
    @Scheduled(cron = "*/10 * * * * *")
    public void daemon_function(){


        List<TradeOrderDTO> tradeOrderDTOList = tradeOrderService.getOrderByStatusCode(StatusCode.PENDING);
        for(TradeOrderDTO tradeOrderDTO: tradeOrderDTOList){
            User user = userService.getUserById(tradeOrderDTO.getUserId());
            Double price = stockMarketService.getPrice(tradeOrderDTO.getStockTickerLabel());
            if(tradeOrderDTO.getBuyOrSell() == BuyOrSell.BUY){
                if(user.getBankAccount().getBalance() >= price * tradeOrderDTO.getStockVolume()
                && price <= tradeOrderDTO.getStockPrice()){
                    stockMarketService.executeBuyOrder(tradeOrderDTO, user, price);
                    log.info("Order number "+ tradeOrderDTO.getTradeOrderId() +" bought at price " + price);
                }else if(price <= tradeOrderDTO.getStockPrice()){
                    stockMarketService.rejectOrder(tradeOrderDTO);
                }
            }else{
                List<Holding> holdings = holdingService.getHoldingByUserId(tradeOrderDTO.getUserId());
                boolean hasHolding = false;
                for(Holding holding: holdings){
                    if(holding.getStockTickerLabel().equalsIgnoreCase(tradeOrderDTO.getStockTickerLabel())){
                        hasHolding = true;
                        if(tradeOrderDTO.getStockVolume() <= holding.getStockVolume()
                                && price >= tradeOrderDTO.getStockPrice()){
                            stockMarketService.executeSellOrder(tradeOrderDTO, user, price);
                            log.info("Order number "+ tradeOrderDTO.getTradeOrderId() +" sold at price " + price);

                        }
                    }
                }
                if(!hasHolding) {
                    stockMarketService.rejectOrder(tradeOrderDTO);
                    log.error("Order number " + tradeOrderDTO.getTradeOrderId() + " rejected.");
                }
            }
        }

    }


    /**
     * Daemon function that rejects all pending trade orders after market closing time of 3:30pm
     */
    @Scheduled(cron = "0 30 15 * * *")
    public void daemon_function_for_rejecting_the_orders(){
        List<TradeOrderDTO> tradeOrderDTOList = tradeOrderService.getOrderByStatusCode(StatusCode.PENDING);
        for(TradeOrderDTO tradeOrderDTO: tradeOrderDTOList){
            stockMarketService.rejectOrder(tradeOrderDTO);
            log.info("Order number "+ tradeOrderDTO.getTradeOrderId() +" rejected automatically");
        }

    }
}
