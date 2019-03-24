package com.kevin.app.entity.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kevin.app.entity.product_tax.BillEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
public class BillResponse {
    private List<ProductTaxResponse> products;
    private BigDecimal priceSubtotal;
    private BigDecimal taxSubtotal;
    private BigDecimal grandTotal;

    public BillResponse(BillEntity billEntity) {
        this.products = billEntity.getProductTaxEntities()
                .stream()
                .map(ProductTaxResponse::new)
                .collect(Collectors.toList());
        this.priceSubtotal = billEntity.getPriceSubtotal();
        this.taxSubtotal = billEntity.getTaxSubtotal();
        this.grandTotal = billEntity.getGrandTotal();
    }
}
