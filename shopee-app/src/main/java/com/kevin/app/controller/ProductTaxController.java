package com.kevin.app.controller;

import com.kevin.app.entity.product.ProductEntity;
import com.kevin.app.entity.request.ProductTaxRequest;
import com.kevin.app.entity.response.BillResponse;
import com.kevin.app.entity.response.ProductTaxResponse;
import com.kevin.app.service.product.IProductManager;
import com.kevin.app.service.tax.ITaxFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/product-taxes")
public class ProductTaxController {

    private IProductManager productManager;
    private ITaxFactory taxFactory;

    @Autowired
    public ProductTaxController(IProductManager productManager,
                                ITaxFactory taxFactory) {
        this.productManager = productManager;
        this.taxFactory = taxFactory;
    }

    @PostMapping()
    public void createProductTax(@RequestBody ProductTaxRequest request) {
        ProductEntity product = new ProductEntity(
                taxFactory.getTaxEntity(request.getTaxCode()),
                request.getName(),
                request.getPrice());
        productManager.persistProductEntity(product);
    }

    @GetMapping()
    public List<ProductTaxResponse> getAllProducts() {
        return productManager.getAllProducts()
                .stream()
                .map(ProductTaxResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("bills")
    public BillResponse getBills() {
        List<ProductTaxResponse> productTaxResponses = productManager.getAllProducts()
                .stream()
                .map(ProductTaxResponse::new)
                .collect(Collectors.toList());
        return new BillResponse(productTaxResponses);
    }
}
