package com.kevin.app.service_impl.product;

import com.kevin.app.dao.product_taxes.ProductTaxes;
import com.kevin.app.dao.products.Products;
import com.kevin.app.dao.products.ProductsDaoService;
import com.kevin.app.entity.product_tax.BillEntity;
import com.kevin.app.entity.product_tax.ProductTaxEntity;
import com.kevin.app.entity.tax.ITaxEntity;
import com.kevin.app.service.product.IProductManager;
import com.kevin.app.service.tax.ITaxFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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


    public void persistProductTaxEntity(ProductTaxEntity productTaxEntity) {
        if (productTaxEntity.getName() == null ||
            productTaxEntity.getName().isEmpty() ||
            productTaxEntity.getPrice() < 0 ||
            productTaxEntity.getTax() == null) {
            throw new IllegalArgumentException("Product attributes missing");
        }

        Products product = new Products(productTaxEntity.getName(), productTaxEntity.getPrice());
        setTaxCode(product, productTaxEntity);
        productsDaoService.persistProducts(product);
    }

    private void setTaxCode(Products products, ProductTaxEntity productTaxEntity) {
        List<ProductTaxes> productTaxes = new ArrayList<>();

        ITaxEntity entity = taxFactory.getTaxEntity(productTaxEntity.getTax().getTaxCode());
        productTaxes.add(new ProductTaxes(products, entity.getTaxCode()));
        products.setProductTaxes(productTaxes);
    }

    public List<ProductTaxEntity> getAllProducts() {
        List<Products> products = productsDaoService.getAllProducts();
        return products.stream().map(product -> {
            ProductTaxEntity.ProductTaxEntityBuilder builder = ProductTaxEntity.builder();

            if (product.getProductTaxes().size() > 0) {
                ITaxEntity taxEntity = taxFactory.getTaxEntity(product.getProductTaxes().get(0).getTaxCode());
                int price = product.getPrice();
                BigDecimal taxAmount = taxEntity.calculateTax(price);

                builder.tax(taxEntity);
                builder.afterTaxAmount(new BigDecimal(price).add(taxAmount));
                builder.taxAmount(taxAmount);
            }

            return builder
                    .price(product.getPrice())
                    .name(product.getName())
                    .build();
        }).collect(Collectors.toList());
    }

    public Optional<BillEntity> createBill() {
        List<ProductTaxEntity> productTaxEntities = getAllProducts();
        if (productTaxEntities.size() <= 0) {
            return Optional.empty();
        }

        BigDecimal priceSubtotal = productTaxEntities
                .stream()
                .map(product -> new BigDecimal(product.getPrice()))
                .reduce(BigDecimal::add)
                .get();

        BigDecimal taxSubtotal = productTaxEntities
                .stream()
                .map(ProductTaxEntity::getTaxAmount)
                .reduce(BigDecimal::add).get();

        BigDecimal grandTotal = priceSubtotal.add(taxSubtotal);

        return Optional.of(new BillEntity(productTaxEntities, priceSubtotal, taxSubtotal, grandTotal));
    }

}
