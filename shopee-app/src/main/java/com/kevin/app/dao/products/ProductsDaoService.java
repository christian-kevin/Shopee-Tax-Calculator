package com.kevin.app.dao.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

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
}
