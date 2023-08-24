package com.maverick.tradingApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maverick.tradingApp.dto.StockPrice;
import com.maverick.tradingApp.dto.StockSymbol;
import com.maverick.tradingApp.dto.StockWithPriceDTO;
import com.maverick.tradingApp.dto.TradeOrderDTO;
import com.maverick.tradingApp.enums.StatusCode;
import com.maverick.tradingApp.model.Holding;
import com.maverick.tradingApp.model.TradeOrder;
import com.maverick.tradingApp.model.User;
import com.maverick.tradingApp.repository.BankAccountRepository;
import com.maverick.tradingApp.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Avinash G, Priyanshu T, Karthik R
 */
@Service
@AllArgsConstructor
@Slf4j
public class StockMarketService {

    @Autowired
    private HoldingService holdingService;
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Value("${stockMarket.symbol.list}")
    private String stockSymbolListURL;

    @Value("${stockMarket.token}")
    private String token;

    @Value("${stockMarket.quote.url}")
    private String stockMarketQuoteURL;
    private static List<StockSymbol> stockSymbols = null;

    public StockMarketService(){
        stockSymbols = new ArrayList<>();
        stockSymbols.add(StockSymbol.builder().symbol("C").description("CITIGROUP").build());
        stockSymbols.add(StockSymbol.builder().symbol("AAPL").description("APPLE INC").build());
        stockSymbols.add(StockSymbol.builder().symbol("GOOG").description("ALPHABET INC").build());
        stockSymbols.add(StockSymbol.builder().symbol("GOOGL").description("ALPHABET INC").build());
        stockSymbols.add(StockSymbol.builder().symbol("ADBE").description("ADOBE INC").build());
        stockSymbols.add(StockSymbol.builder().symbol("TSLA").description("TESLA INC").build());
        stockSymbols.add(StockSymbol.builder().symbol("WMT").description("WALMART INC").build());
        stockSymbols.add(StockSymbol.builder().symbol("AMZN").description("AMAZON.COM INC").build());
        stockSymbols.add(StockSymbol.builder().symbol("NKE").description("NIKE INC").build());
        stockSymbols.add(StockSymbol.builder().symbol("MCD").description("MCDONALD'S CORP").build());
        stockSymbols.add(StockSymbol.builder().symbol("NVDA").description("NVIDIA CORP").build());
    }

    /**
     * Returns a list of stocks symbols (not recommended)
     * @return list of stock symbols
     */
    public List<StockSymbol> getStockSymbols(){
        if(StockMarketService.stockSymbols != null)return StockMarketService.stockSymbols;
        String uri = stockSymbolListURL+token;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        JSONArray jsonArray = new JSONArray(result);
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockSymbol> stockSymbols = new ArrayList<>();
        jsonArray.forEach(jsonObj->{
            try {
                stockSymbols.add(objectMapper.readValue(jsonObj.toString(), StockSymbol.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        StockMarketService.stockSymbols = stockSymbols;
        return stockSymbols;
    }

    /**
     * Get a list of popular stocks with their respective stock prices.
     * @return List of stocks
     */
    public List<StockWithPriceDTO> getPopularStocks() {
        List<StockWithPriceDTO> stocks = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (StockSymbol stockSymbol : stockSymbols) {
            String uri = String.format(this.stockMarketQuoteURL,stockSymbol.getSymbol(),this.token);
            try {
                StockPrice stockPrice = objectMapper.readValue((new RestTemplate()).getForObject(uri, String.class)
                , StockPrice.class);
                stocks.add(StockWithPriceDTO.builder().stockSymbol(stockSymbol).stockPrice(stockPrice).build());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return stocks;
    }

    public Double getPrice(String stockSymbol){
        String url = String.format(stockMarketQuoteURL,stockSymbol,token);
        Map<String, Object> map = JsonParserFactory
                .getJsonParser()
                .parseMap((new RestTemplate())
                        .getForObject(url,String.class));
        return (Double) (map.get("c"));
    }

    @Transactional
    public void executeSellOrder(TradeOrderDTO tradeOrderDTO, User user, Double price) {
        TradeOrder tradeOrder = orderRepository.findById(tradeOrderDTO.getTradeOrderId()).get();
        user.getBankAccount().deposit((long) (price * tradeOrderDTO.getStockVolume()));
        bankAccountRepository.save(user.getBankAccount());
        Holding holding = Holding
                .builder()
                .stockTickerLabel(tradeOrderDTO.getStockTickerLabel())
                .stockPrice(price)
                .stockVolume(tradeOrderDTO.getStockVolume())
                .userId(tradeOrderDTO.getUserId())
                .build();
        holdingService.removeHolding(holding);
        tradeOrder.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
        tradeOrder.setStockPrice(tradeOrderDTO.getStockPrice());
        tradeOrder.setStockVolume(tradeOrderDTO.getStockVolume());
        tradeOrder.setStockStatusCode(StatusCode.EXECUTED);
        orderRepository.save(tradeOrder);
    }

    @Transactional
    public void executeBuyOrder(TradeOrderDTO tradeOrderDTO, User user, Double price) {
        TradeOrder tradeOrder = orderRepository.findById(tradeOrderDTO.getTradeOrderId()).get();
        user.getBankAccount().withdraw((long)(price * tradeOrderDTO.getStockVolume()));
        bankAccountRepository.save(user.getBankAccount());
        Holding holding = Holding
                .builder()
                .stockTickerLabel(tradeOrderDTO.getStockTickerLabel())
                .stockPrice(price)
                .stockVolume(tradeOrderDTO.getStockVolume())
                .userId(tradeOrderDTO.getUserId())
                .build();
        holdingService.createHolding(holding);
        tradeOrder.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
        tradeOrder.setStockPrice(tradeOrderDTO.getStockPrice());
        tradeOrder.setStockVolume(tradeOrderDTO.getStockVolume());
        tradeOrder.setStockStatusCode(StatusCode.EXECUTED);
        orderRepository.save(tradeOrder);
    }

    @Transactional
    public void rejectOrder(TradeOrderDTO tradeOrderDTO) {
        TradeOrder tradeOrder = orderRepository.getReferenceById(tradeOrderDTO.getTradeOrderId());
        tradeOrder.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
        tradeOrder.setStockStatusCode(StatusCode.REJECTED);
        orderRepository.save(tradeOrder);
    }
}
