package com.kevin.app.entity.tax;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class FoodAndBeverageTax implements ITax {
    private int taxCode = 1;
    private String type = "Food & Beverage";
    private boolean refundable = true;

    @Override
    public BigDecimal calculateTax(int price) {
        return new BigDecimal( 0.1 * price);
    }
}
