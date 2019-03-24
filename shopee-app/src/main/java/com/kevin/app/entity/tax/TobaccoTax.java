package com.kevin.app.entity.tax;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TobaccoTax implements ITax {
    private int taxCode = 2;
    private String type = "Tobacco";
    private boolean isRefundable = false;

    @Override
    public BigDecimal calculateTax(int price) {
        return new BigDecimal(10 + (0.2 * price));
    }
}
