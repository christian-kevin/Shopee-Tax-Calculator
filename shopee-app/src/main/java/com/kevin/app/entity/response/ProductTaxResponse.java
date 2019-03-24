package com.kevin.app.entity.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kevin.app.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
public class ProductTaxResponse {
    private String name;
    private int taxCode;
    private String type;
    private boolean refundable;
    private int price;
    private BigDecimal taxAmount;
    private BigDecimal amount;

    public ProductTaxResponse(ProductEntity productEntity) {
        this.name = productEntity.getName();
        this.taxCode = productEntity.getTaxCode();
        this.type = productEntity.getTax().getType();
        this.price = productEntity.getPrice();
        this.refundable = productEntity.getTax().isRefundable();
        this.taxAmount = productEntity.getTaxAmount();
        this.amount = productEntity.getAmount();
    }
}
