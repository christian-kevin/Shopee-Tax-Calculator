package com.kevin.app.dao.products;

import com.kevin.app.dao.BaseDao;
import com.kevin.app.dao.product_taxes.ProductTaxes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="products")
@Getter
@NoArgsConstructor
public class Products extends BaseDao {
    @Column(name="name", nullable = false)
    private String name;

    @Column(name="price", nullable = false)
    private int price;

    @OneToMany(mappedBy="product", cascade=CascadeType.ALL)
    @Setter
    private List<ProductTaxes> productTaxes;

    public Products(String name, int price) {
        this.name = name;
        this.price = price;
    }
}