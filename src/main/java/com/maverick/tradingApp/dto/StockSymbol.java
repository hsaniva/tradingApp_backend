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
public class StockSymbol {
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("description")
    private String description;

    @JsonProperty("displaySymbol")
    private String displaySymbol;
}
