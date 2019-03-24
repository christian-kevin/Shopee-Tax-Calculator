package com.kevin.app.service_impl.product;

import com.kevin.app.dao.product_taxes.ProductTaxes;
import com.kevin.app.dao.product_taxes.ProductTaxesDaoService;
import com.kevin.app.dao.products.Products;
import com.kevin.app.dao.products.ProductsDaoService;
import com.kevin.app.entity.product.ProductEntity;
import com.kevin.app.entity.tax.ITaxEntity;
import com.kevin.app.service.product.IProductManager;
import com.kevin.app.service.tax.ITaxFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductManagerImpl implements IProductManager {
    private ITaxFactory taxFactory;
    private ProductsDaoService productsDaoService;

    @Autowired
    public ProductManagerImpl(ITaxFactory taxFactory,
                              ProductsDaoService productsDaoService) {
        this.taxFactory = taxFactory;
        this.productsDaoService = productsDaoService;
    }


    public void persistProductEntity(ProductEntity productEntity) {
        Products product = new Products(productEntity.getName(), productEntity.getPrice());
        setTaxCode(product, productEntity);
        productsDaoService.persistProducts(product);
    }

    private void setTaxCode(Products products, ProductEntity productEntity) {
        List<ProductTaxes> productTaxes = new ArrayList<>();

        ITaxEntity entity = taxFactory.getTaxEntity(productEntity.getTaxCode());
        productTaxes.add(new ProductTaxes(products, entity.getTaxCode()));
        products.setProductTaxes(productTaxes);
    }

    public List<ProductEntity> getAllProducts() {
        List<Products> products = productsDaoService.getAllProducts();
        return products.stream().map(product -> {
            ITaxEntity taxEntity = null;

            if (product.getProductTaxes().size() > 0) {
                taxEntity = taxFactory.getTaxEntity(product.getProductTaxes().get(0).getTaxCode());
            }

            return new ProductEntity(taxEntity, product.getName(), product.getPrice());
        }).collect(Collectors.toList());
    }
}
