package com.kevin.app.entity.product_tax;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class BillEntity {
    List<ProductTaxEntity> productTaxEntities;
    private BigDecimal priceSubtotal;
    private BigDecimal taxSubtotal;
    private BigDecimal grandTotal;
}
