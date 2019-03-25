package com.kevin.app.service.product;

import com.kevin.app.entity.product_tax.BillEntity;
import com.kevin.app.entity.product_tax.ProductTaxEntity;

import java.util.List;
import java.util.Optional;

public interface IProductManager {
    void persistProductTaxEntity(ProductTaxEntity productTaxEntity);
    List<ProductTaxEntity> getAllProducts();
    Optional<BillEntity> createBill();
}
