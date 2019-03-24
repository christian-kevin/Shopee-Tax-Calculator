package com.kevin.app.entity.product;

import com.kevin.app.entity.tax.ITaxEntity;
import com.kevin.app.service_impl.product.entity.IProductTaxEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductEntity implements IProductTaxEntity {
    private ITaxEntity tax;
    private String name;
    private int price;

    public int getTaxCode() {
        return tax.getTaxCode();
    }
}
