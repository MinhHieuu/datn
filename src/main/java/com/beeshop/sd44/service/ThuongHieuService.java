package com.beeshop.sd44.service;


import com.beeshop.sd44.entity.ThuongHieu;
import com.beeshop.sd44.repository.ThuongHieuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ThuongHieuService {
    @Autowired
    private ThuongHieuRepo repo;

    public ThuongHieu hanldeSave(ThuongHieu thuongHieu) {
        return this.repo.save(thuongHieu);
    }

    public List<ThuongHieu> getAll() {
        return this.repo.findAll();
    }

    public ThuongHieu getById(UUID id){
        Optional<ThuongHieu> thuongHieu = this.repo.findById(id);
        if(thuongHieu.isPresent()) {
            return thuongHieu.get();
        }
        return null;
    }

    public void handleDelete(ThuongHieu thuongHieu) {
        this.repo.delete(thuongHieu);
    }

    public boolean isNameExit(String name) {
        return repo.existsByTen(name);
    }
}
