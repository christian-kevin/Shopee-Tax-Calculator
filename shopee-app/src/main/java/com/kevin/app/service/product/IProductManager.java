package com.kevin.app.service.product;

import com.kevin.app.entity.product.ProductEntity;

import java.util.List;

public interface IProductManager {
    void persistProductEntity(ProductEntity productEntity);
    List<ProductEntity> getAllProducts();
}
