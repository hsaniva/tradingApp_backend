package com.maverick.tradingApp.enums;

import java.util.Map;

public enum StatusCode {
    PENDING(0), EXECUTED(1), REJECTED(2), CANCELLED(3);

    private final int statusCodeValue;

    StatusCode(final int statusCodeValue) {
        this.statusCodeValue = statusCodeValue;
    }
    public int getStatusCodeValue(){
        return this.statusCodeValue;
    }
}
