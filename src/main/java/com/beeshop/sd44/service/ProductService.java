package com.beeshop.sd44.service;

import com.beeshop.sd44.entity.SanPham;
import com.beeshop.sd44.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepo repo;
    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }

    public List<SanPham> getAll() {
        return this.repo.findAll();
    }

    public boolean isNameExit(String name) {
        return this.repo.existsByTen(name);
    }
}
