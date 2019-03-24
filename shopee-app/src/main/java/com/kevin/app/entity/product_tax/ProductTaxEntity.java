package com.kevin.app.entity.product_tax;

import com.kevin.app.entity.tax.ITaxEntity;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProductTaxEntity {
    private ITaxEntity tax;
    private String name;
    private int price;
    private BigDecimal afterTaxAmount;
    private BigDecimal taxAmount;
}
