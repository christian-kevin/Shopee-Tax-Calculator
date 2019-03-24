package com.kevin.app.entity.product;

import com.kevin.app.entity.tax.ITaxEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductEntity {
    private ITaxEntity tax;
    private String name;
    private int price;
}
