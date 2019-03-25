package com.kevin.app.entity.product_tax;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BillEntity {
    List<ProductTaxEntity> productTaxEntities;
    private BigDecimal priceSubtotal;
    private BigDecimal taxSubtotal;
    private BigDecimal grandTotal;
}
