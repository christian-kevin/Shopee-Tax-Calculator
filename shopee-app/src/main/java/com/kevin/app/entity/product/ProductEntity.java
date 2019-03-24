package com.kevin.app.entity.product;

import com.kevin.app.entity.tax.ITaxEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductEntity {
    private ITaxEntity tax;
    private String name;
    private int price;

    public int getTaxCode() {
        return tax.getTaxCode();
    }

    public BigDecimal getAmount() {
        return new BigDecimal(price).add(tax.calculateTax(price));
    }

    public BigDecimal getTaxAmount() {
        return tax.calculateTax(price);
    }
}
