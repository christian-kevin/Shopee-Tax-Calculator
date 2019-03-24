package com.kevin.app.service.product;

import com.kevin.app.service_impl.product.entity.IProductTaxEntity;

public interface IProductManager {
    void persistProductEntity(IProductTaxEntity productEntity);
}
