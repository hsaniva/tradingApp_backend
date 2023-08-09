package com.maverick.tradingApp.service;

import ch.qos.logback.core.net.SyslogOutputStream;
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
import java.util.Map;

@Service
@Profile("Karthik")
public class Scheduler {

    @Scheduled(fixedRate = 120000)
    public void task1(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        //getting the latest price
        Double price = getPrice("AAPL");
        System.out.println("Completed task1 : "+dtf.format(now)+price.toString());
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
