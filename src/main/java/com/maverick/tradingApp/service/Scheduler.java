package com.maverick.tradingApp.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.maverick.tradingApp.dto.TradeOrderDTO;
import com.maverick.tradingApp.enums.BuyOrSell;
import com.maverick.tradingApp.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class Scheduler {

    @Autowired
    private final TradeOrderService tradeOrderService;
    @Scheduled(fixedRate = 30000)
    public void daemon_function(){
        /**
         * Daemon function that runs every 30 seconds and checks if any PENDING trade orders can be execute
         * This Daemon is active only between 9am and 3:30pm
         */

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        if(now.getHour() < 9 || (now.getHour() >= 15 && now.getMinute() >= 30))return;
        List<TradeOrderDTO> tradeOrderDTOList = tradeOrderService.getOrderByStatusCode(StatusCode.PENDING);

        for(TradeOrderDTO tradeOrderDTO: tradeOrderDTOList){
            Double price = getPrice(tradeOrderDTO.getStockTickerLabel());
            if(tradeOrderDTO.getBuyOrSell() == BuyOrSell.BUY){
                if(price <= tradeOrderDTO.getStockPrice()){
                    tradeOrderDTO.setStockStatusCode(StatusCode.EXECUTED);
                    tradeOrderService.updateTradeOrder(tradeOrderDTO);
                    log.info("Order number "+ tradeOrderDTO.getTradeOrderId() +" bought at price " + price);
                }
            }else{
                if(price >= tradeOrderDTO.getStockPrice()){
                    tradeOrderDTO.setStockStatusCode(StatusCode.EXECUTED);
                    tradeOrderService.updateTradeOrder(tradeOrderDTO);
                    log.info("Order number "+ tradeOrderDTO.getTradeOrderId() +" sold at price " + price);
                }
            }
        }

    }

    @Scheduled(cron = "0 30 15 * * *")
    public void daemon_function_for_rejecting_the_orders(){
        /**
         * Daemon function that rejects all pending trade orders after market closing time of 3:30pm
         */

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        List<TradeOrderDTO> tradeOrderDTOList = tradeOrderService.getOrderByStatusCode(StatusCode.PENDING);

        for(TradeOrderDTO tradeOrderDTO: tradeOrderDTOList){
            tradeOrderDTO.setStockStatusCode(StatusCode.REJECTED);
            tradeOrderService.updateTradeOrder(tradeOrderDTO);
            log.info("Order number "+ tradeOrderDTO.getTradeOrderId() +" rejected automatically");
        }

    }
    public Double getPrice(String symbol){
        String url = String.format("https://finnhub.io/api/v1/quote?symbol=%s&token=cj9k3npr01qgvpdt3a9gcj9k3npr01qgvpdt3aa0",symbol);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url,String.class);
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = springParser.parseMap(result);
        return (Double)map.get("c");

    }

}
