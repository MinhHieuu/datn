package com.beeshop.sd44.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCors {
    @GetMapping("/")
    public String home () {
        return "tao da lam duoc";
    }
}
