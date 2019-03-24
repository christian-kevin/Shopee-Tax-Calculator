package com.kevin.app.entity.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
public class BillResponse {
    private List<ProductTaxResponse> products;
    private BigDecimal priceSubtotal;
    private BigDecimal taxSubtotal;
    private BigDecimal grandTotal;

    public BillResponse(List<ProductTaxResponse> products) {
        this.products = products;
        this.priceSubtotal = products.stream().map(product -> new BigDecimal(product.getPrice())).reduce(BigDecimal::add).get();
        this.taxSubtotal = products.stream().map(ProductTaxResponse::getTaxAmount).reduce(BigDecimal::add).get();
        this.grandTotal = priceSubtotal.add(taxSubtotal);
    }
}
