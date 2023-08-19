package com.maverick.tradingApp.dto;

/**
 * @author Avinash G, Karthik R, Priyanshu T
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockPrice {

    @JsonProperty("c")
    private double currentPrice;
    @JsonProperty("d")
    private double change;
    @JsonProperty("dp")
    private double percentChange;
    @JsonProperty("h")
    private double highPrice;
    @JsonProperty("l")
    private double lowPrice;
    @JsonProperty("o")
    private double openPrice;
    @JsonProperty("pc")
    private double previousClose;
    @JsonProperty("t")
    private Long timestamp;
}
