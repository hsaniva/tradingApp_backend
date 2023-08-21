package com.maverick.tradingApp.service;

import com.maverick.tradingApp.dto.TradeOrderDTO;
import com.maverick.tradingApp.enums.BuyOrSell;
import com.maverick.tradingApp.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
@RequiredArgsConstructor
public class Scheduler {

    @Autowired
    private TradeOrderService tradeOrderService;

    @Value("${stockMarket.token}")
    private String token;

    @Value("${stockMarket.quote.url}")
    private String stockMarketQuoteURL;

    /**
     * Daemon function that runs every 30 seconds and checks if any PENDING trade orders can be executed
     * This Daemon is active only between 9am and 3:30pm
     */
    @Scheduled(cron = "*/10 * * * * *")
    public void daemon_function(){


        List<TradeOrderDTO> tradeOrderDTOList = tradeOrderService.getOrderByStatusCode(StatusCode.PENDING);

        for(TradeOrderDTO tradeOrderDTO: tradeOrderDTOList){
            Double price = getPrice(tradeOrderDTO.getStockTickerLabel());
            if(tradeOrderDTO.getBuyOrSell() == BuyOrSell.BUY){
                if(price <= tradeOrderDTO.getStockPrice()){
                    tradeOrderDTO.setStockPrice(price);
                    tradeOrderService.executeOrder(tradeOrderDTO);
                    log.info("Order number "+ tradeOrderDTO.getTradeOrderId() +" bought at price " + price);
                }
            }else{
                if(price >= tradeOrderDTO.getStockPrice()){
                    tradeOrderDTO.setStockStatusCode(StatusCode.EXECUTED);
                    tradeOrderDTO.setStockPrice(price);
                    tradeOrderService.executeOrder(tradeOrderDTO);
                    log.info("Order number "+ tradeOrderDTO.getTradeOrderId() +" sold at price " + price);
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
            tradeOrderService.rejectOrder(tradeOrderDTO);
            log.info("Order number "+ tradeOrderDTO.getTradeOrderId() +" rejected automatically");
        }

    }
    public Double getPrice(String symbol){
        String url = String.format(stockMarketQuoteURL,symbol,token);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url,String.class);
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = springParser.parseMap(result);

        return (Double) (map.get("c"));

    }

}
