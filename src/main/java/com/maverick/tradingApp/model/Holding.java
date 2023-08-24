package com.maverick.tradingApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "stockHolding")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Holding {
    @Id
    private String stockTickerLabel;
    private double stockPrice;
    private int stockVolume;
    private String userId;

}
