package com.beeshop.sd44.service;


import com.beeshop.sd44.entity.Brand;
import com.beeshop.sd44.repository.BrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BrandService {
    @Autowired
    private BrandRepo repo;

    public Brand hanldeSave(Brand brand) {
        return this.repo.save(brand);
    }

    public List<Brand> getAll() {
        return this.repo.findAll();
    }

    public Brand getById(UUID id){
        Optional<Brand> thuongHieu = this.repo.findById(id);
        if(thuongHieu.isPresent()) {
            return thuongHieu.get();
        }
        return null;
    }

    public void handleDelete(Brand brand) {
        this.repo.delete(brand);
    }

    public boolean isNameExit(String name) {
        return repo.existsByName(name);
    }
}
