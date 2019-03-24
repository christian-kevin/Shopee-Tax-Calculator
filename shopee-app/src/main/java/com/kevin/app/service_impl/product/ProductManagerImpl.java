package com.kevin.app.service_impl.product;

import com.kevin.app.dao.product_taxes.ProductTaxes;
import com.kevin.app.dao.product_taxes.ProductTaxesDaoService;
import com.kevin.app.dao.products.Products;
import com.kevin.app.dao.products.ProductsDaoService;
import com.kevin.app.entity.product.ProductEntity;
import com.kevin.app.entity.tax.ITaxEntity;
import com.kevin.app.service.product.IProductManager;
import com.kevin.app.service.tax.ITaxFactory;
import com.kevin.app.service_impl.product.entity.IProductTaxEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductManagerImpl implements IProductManager {
    private ITaxFactory taxFactory;
    private ProductsDaoService productsDaoService;
    private ProductTaxesDaoService productTaxesDaoService;

    @Autowired
    public ProductManagerImpl(ITaxFactory taxFactory,
                              ProductsDaoService productsDaoService,
                              ProductTaxesDaoService productTaxesDaoService) {
        this.taxFactory = taxFactory;
        this.productsDaoService = productsDaoService;
        this.productTaxesDaoService = productTaxesDaoService;
    }


    public void persistProductEntity(IProductTaxEntity productEntity) {
        Products product = new Products(productEntity.getName(), productEntity.getPrice());
        setTaxCode(product, productEntity);
        productsDaoService.persistProducts(product);
    }

    private void setTaxCode(Products products, IProductTaxEntity productEntity) {
        Set<ProductTaxes> productTaxesSet = new HashSet<>();

        ITaxEntity entity = taxFactory.getTaxEntity(productEntity.getTaxCode());
        productTaxesSet.add(new ProductTaxes(products, entity.getTaxCode()));
        products.setProductTaxes(productTaxesSet);
    }
}
