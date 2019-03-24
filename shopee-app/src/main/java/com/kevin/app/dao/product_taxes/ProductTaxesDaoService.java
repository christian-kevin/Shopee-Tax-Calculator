package com.kevin.app.dao.product_taxes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class ProductTaxesDaoService {
    private EntityManager em;

    @Autowired
    public ProductTaxesDaoService(EntityManager em) {
        this.em = em;
    }

    @Modifying
    @Transactional
    public void persistProductTaxes(ProductTaxes productTax) {
        em.persist(productTax);
    }
}
