package com.maverick.tradingApp.dto;
/**
 * @author Avinash G, Karthik R, Priyanshu T
 */

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockWithPriceDTO {
    private StockSymbol stockSymbol;
    private StockPrice stockPrice;
}
