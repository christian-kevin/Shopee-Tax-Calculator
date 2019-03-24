package com.kevin.app.controller;

import com.kevin.app.entity.product.ProductEntity;
import com.kevin.app.service.product.IProductManager;
import com.kevin.app.service.tax.ITaxFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductTaxController {

    private IProductManager productManager;
    private ITaxFactory taxFactory;

    @Autowired
    public ProductTaxController(IProductManager productManager,
                                ITaxFactory taxFactory) {
        this.productManager = productManager;
        this.taxFactory = taxFactory;
    }

    @GetMapping(path = "/insert")
    public String testCreateProduct() {
        ProductEntity product = new ProductEntity(taxFactory.getTaxEntity(1), "makanan", 2000);
        productManager.persistProductEntity(product);
        return "baba";
    }
}
