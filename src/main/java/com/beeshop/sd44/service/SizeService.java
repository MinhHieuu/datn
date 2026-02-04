package com.beeshop.sd44.service;

import com.beeshop.sd44.entity.Size;
import com.beeshop.sd44.repository.SizeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SizeService {
    @Autowired
    private SizeRepo repo;
    public Size hanldeSave(Size size) {
        return this.repo.save(size);
    }

    public List<Size> getAll() {
        return this.repo.findAll();
    }

    public Size getById(UUID id){
        Optional<Size> size = this.repo.findById(id);
        if(size.isPresent()) {
            return size.get();
        }
        return null;
    }

    public void handleDelete(Size size) {
        this.repo.delete(size);
    }

    public boolean isNameExit(String name) {
        return repo.existsByTen(name);
    }
}
