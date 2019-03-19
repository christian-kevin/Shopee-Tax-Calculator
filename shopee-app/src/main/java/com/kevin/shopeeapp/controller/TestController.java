package com.kevin.shopeeapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class TestController {

    @GetMapping
    public final String helloWorld() throws UnknownHostException {
        return "Hello World " + InetAddress.getLocalHost().getHostAddress();
    }

}
