package com.beeshop.sd44.service;

import com.beeshop.sd44.entity.Product;
import com.beeshop.sd44.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepo repo;
    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }

    public List<Product> getAll() {
        return this.repo.findAll();
    }

    public boolean isNameExit(String name) {
        return this.repo.existsByName(name);
    }
}
