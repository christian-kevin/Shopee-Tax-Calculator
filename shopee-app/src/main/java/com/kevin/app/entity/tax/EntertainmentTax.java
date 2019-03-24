package com.kevin.app.entity.tax;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class EntertainmentTax implements ITax {
    private int taxCode = 3;
    private String type = "Entertainment";
    private boolean isRefundable = false;

    @Override
    public BigDecimal calculateTax(int price) {
        if (0 < price && price < 100) {
            return new BigDecimal(0);
        } else if (price >= 100) {
            return new BigDecimal(0.1 * (price - 100));
        } else {
            throw new IllegalArgumentException("Price is not valid.");
        }
    }
}
