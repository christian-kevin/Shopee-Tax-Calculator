package com.kevin.app.entity.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kevin.app.entity.product_tax.ProductTaxEntity;
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
    private BigDecimal afterTaxAmount;

    public ProductTaxResponse(ProductTaxEntity productTaxEntity) {
        if (productTaxEntity.getTax() != null) {
            this.taxCode = productTaxEntity.getTax().getTaxCode();
            this.type = productTaxEntity.getTax().getType();
            this.refundable = productTaxEntity.getTax().isRefundable();
            this.taxAmount = productTaxEntity.getTaxAmount();
            this.afterTaxAmount = productTaxEntity.getAfterTaxAmount();
        }

        this.price = productTaxEntity.getPrice();
        this.name = productTaxEntity.getName();
    }
}
