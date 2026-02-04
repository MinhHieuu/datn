package com.beeshop.sd44.service;


import com.beeshop.sd44.entity.MauSac;
import com.beeshop.sd44.repository.MauSacRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MauSacService {
    @Autowired
    private MauSacRepo repo;

    public MauSac hanldeSave(MauSac mauSac) {
        return this.repo.save(mauSac);
    }

    public List<MauSac> getAll() {
        return this.repo.findAll();
    }

    public MauSac getById(UUID id){
        Optional<MauSac> mauSac = this.repo.findById(id);
        if(mauSac.isPresent()) {
            return mauSac.get();
        }
        return null;
    }

    public void handleDelete(MauSac mauSac) {
        this.repo.delete(mauSac);
    }

    public boolean isNameExit(String name) {
        return repo.existsByTen(name);
    }
}
