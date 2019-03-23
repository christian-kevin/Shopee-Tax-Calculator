package com.kevin.app.dao;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class Products extends BaseDao {
    private String name;
}