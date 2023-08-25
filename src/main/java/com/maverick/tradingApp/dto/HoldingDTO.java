package com.maverick.tradingApp.dto;

import jakarta.persistence.Id;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HoldingDTO {
    private String stockTickerLabel;
    private double stockPrice;
    private int stockVolume;
    private String userId;
    private double stockCurrentPrice;

}
