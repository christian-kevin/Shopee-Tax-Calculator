package com.kevin.app.dao.product_taxes;

import com.kevin.app.dao.BaseDao;
import com.kevin.app.dao.products.Products;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Table(name="product_taxes")
@Entity
@Getter
@AllArgsConstructor
public class ProductTaxes extends BaseDao {
    @ManyToOne
    @JoinColumn(name="product_id")
    private Products product;

    @Column(name="tax_code", nullable = false)
    private int taxCode;
}
