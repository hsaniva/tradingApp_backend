package com.maverick.tradingApp.service;

import com.maverick.tradingApp.model.Holding;
import com.maverick.tradingApp.repository.HoldingRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class HoldingService {

    @Autowired
    private final HoldingRepository holdingRepository;


    public void createHolding(Holding holding){
        holdingRepository.save(holding);
    }

    public void addHolding(Holding holding){
        Holding holdingBO = holdingRepository.getReferenceById(holding.getStockTickerLabel());
        Double avg = (holding.getStockPrice() + holdingBO.getStockPrice())/2;
        holdingBO.setStockPrice(avg);
        holdingBO.setStockVolume(holdingBO.getStockVolume() + holding.getStockVolume());
        holdingRepository.save(holding);
    }

    public List<Holding> getHoldingByUserId(String userId){
        return holdingRepository.findByUserId(userId);
    }

    public void removeHolding(Holding holding){
        Holding holdingBO = holdingRepository.getReferenceById(holding.getStockTickerLabel());
        holdingBO.setStockVolume(holdingBO.getStockVolume() - holding.getStockVolume());
        holdingRepository.save(holding);
    }
}
