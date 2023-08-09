package com.maverick.tradingApp.enums;

import java.util.Map;

public enum BuyOrSell {
    BUY(0), SELL(1);

    private final int buyOrSellValue;

    BuyOrSell(final int buyOrSellValue) {
        this.buyOrSellValue = buyOrSellValue;
    }

    public int getBuyOrSellValue() {
        return this.buyOrSellValue;
    }
}
