package com.maverick.tradingApp.controller;

import com.maverick.tradingApp.dto.StockSymbol;
import com.maverick.tradingApp.dto.StockWithPriceDTO;
import com.maverick.tradingApp.service.StockMarketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Avinash G, Karthik R, Priyanshu T
 */
@RestController
@RequestMapping("/api/market")
@AllArgsConstructor
@CrossOrigin
public class StockMarketController {

    private StockMarketService stockMarketService;

    /**
     * Get list of all stock symbols ( not recommended )
     * @return list of all stock symbols
     */
    @GetMapping("/symbol/")
    @ResponseStatus(HttpStatus.OK)
    public List<StockSymbol> getStockSymbols(){
        return stockMarketService.getStockSymbols();
    }

    @GetMapping("/popular")
    @ResponseStatus(HttpStatus.OK)
    public List<StockWithPriceDTO> getPopularStocks(){
        return stockMarketService.getPopularStocks();
    }



}
