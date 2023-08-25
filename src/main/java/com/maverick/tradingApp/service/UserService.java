package com.maverick.tradingApp.service;
/**
 * @author Avinash G, Karthik R, Priyanshu T
 */

import com.maverick.tradingApp.dto.HoldingDTO;
import com.maverick.tradingApp.dto.Portfolio;
import com.maverick.tradingApp.dto.TradeOrderDTO;
import com.maverick.tradingApp.dto.UserDTO;
import com.maverick.tradingApp.helperFunctions.ObjectConversionHelper;
import com.maverick.tradingApp.model.Holding;
import com.maverick.tradingApp.model.User;
import com.maverick.tradingApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HoldingService holdingService;
    @Autowired
    private final StockMarketService stockMarketService;

    @Autowired
    private final TradeOrderService tradeOrderService;

    /**
     * returns list of all users.
     * @return list of all users.
     */
    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(ObjectConversionHelper::BOToDTO).toList();
    }

    /**
     * Creates user
     * @param userDTO
     */
    public void createUser(UserDTO userDTO) {
        userRepository.save(ObjectConversionHelper.DTOToBO(userDTO));
    }

    public User getUserById(String userID){
        return userRepository.findById(userID).get();
    }

    public Portfolio getUserPortfolio(String userId) {
        User user = getUserById(userId);
        List<Holding> holdings = holdingService.getHoldingByUserId(userId);
        List<TradeOrderDTO> tradeOrderDTOs = tradeOrderService.getOrderByUserId(userId);

        Portfolio portfolio = new Portfolio();
        Long investedAmt = 0L;
        Long presentAmt = 0L;
        int totalStockCnt = 0;
        portfolio.setUserDTO(ObjectConversionHelper.BOToDTO(user));
        List<HoldingDTO> holdingDTOS = new ArrayList<>();
        for(Holding holding: holdings){
            investedAmt += (long)(holding.getStockPrice() * holding.getStockVolume());
            Double price = stockMarketService.getPrice(holding.getStockTickerLabel());
            presentAmt += (long)(price * holding.getStockVolume());
            totalStockCnt += holding.getStockVolume();
            HoldingDTO holdingDTO = ObjectConversionHelper.BOToDTO(holding);
            holdingDTO.setStockCurrentPrice(price);
            holdingDTOS.add(holdingDTO);
        }
        portfolio.setTradeOrderDTOs(tradeOrderDTOs);
        portfolio.setUnrealizedProfitOrLoss(((double)(presentAmt - investedAmt)/((double)investedAmt))*100.0);
        portfolio.setInvestedAmount(investedAmt);
        portfolio.setPresentAmount(presentAmt);
        portfolio.setHoldings(holdingDTOS);
        portfolio.setStocksQuantity(totalStockCnt);
        return portfolio;
    }
}
