package com.kevin.app.controller;

import com.kevin.app.entity.product_tax.BillEntity;
import com.kevin.app.entity.product_tax.ProductTaxEntity;
import com.kevin.app.entity.request.ProductTaxRequest;
import com.kevin.app.entity.response.BillResponse;
import com.kevin.app.entity.response.ProductTaxResponse;
import com.kevin.app.service.product.IProductManager;
import com.kevin.app.service.tax.ITaxFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public ResponseEntity createProductTax(@RequestBody ProductTaxRequest request) {
        if (!validateCreateTaxRequest(request)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        ProductTaxEntity product = ProductTaxEntity.builder()
                .tax(taxFactory.getTaxEntity(request.getTaxCode()))
                .name(request.getName())
                .price(request.getPrice())
                .build();
        productManager.persistProductTaxEntity(product);
        return new ResponseEntity(HttpStatus.OK);
    }

    private boolean validateCreateTaxRequest(ProductTaxRequest request) {
        if (request.getName() == null || request.getPrice() == null || request.getTaxCode() == null ) {
            return false;
        }

        try {
            taxFactory.getTaxEntity(request.getTaxCode());
        } catch (Exception e) {
            return false;
        }

        return true;
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
        Optional<BillEntity> billOpt = productManager.createBill();
        return billOpt.isPresent() ? new BillResponse(billOpt.get()) : new BillResponse();
    }
}
