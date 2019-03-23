package com.kevin.app.controller;

import com.kevin.app.dao.products.Products;
import com.kevin.app.dao.products.ProductsDaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@Slf4j
public class TestController {

    private ProductsDaoService productsDaoService;

    @Autowired
    public TestController(ProductsDaoService productsDaoService) {
        this.productsDaoService = productsDaoService;
    }

    @GetMapping
    public String helloWorld() throws UnknownHostException {
        return "Hello World " + InetAddress.getLocalHost().getHostAddress();
    }

    @GetMapping(path = "/insert")
    public String testCreateProduct() {
        Products products = new Products("makanan", 2000);
        productsDaoService.persistProducts(products);
        return "baba";
    }
}
