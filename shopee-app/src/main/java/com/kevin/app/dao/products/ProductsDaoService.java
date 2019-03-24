package com.kevin.app.dao.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductsDaoService {
    private EntityManager em;

    @Autowired
    public ProductsDaoService(EntityManager em) {
        this.em = em;
    }

    @Modifying
    @Transactional
    public void persistProducts(Products product) {
        em.persist(product);
    }

    public List<Products> getAllProducts() {
        Query q = em.createQuery("select p from Products p order by p.createdAt", Products.class);

        @SuppressWarnings("unchecked")
        List<Products> products = q.getResultList();

        if (products.size() == 0) return Collections.emptyList();

        return products;
    }
}
