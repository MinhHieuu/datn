package com.beeshop.sd44.service;


import com.beeshop.sd44.entity.Color;
import com.beeshop.sd44.repository.ColorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ColorService {
    @Autowired
    private ColorRepo repo;

    public Color hanldeSave(Color color) {
        return this.repo.save(color);
    }

    public List<Color> getAll() {
        return this.repo.findAll();
    }

    public Color getById(UUID id){
        Optional<Color> mauSac = this.repo.findById(id);
        if(mauSac.isPresent()) {
            return mauSac.get();
        }
        return null;
    }

    public void handleDelete(Color color) {
        this.repo.delete(color);
    }

    public boolean isNameExit(String name) {
        return repo.existsByName(name);
    }
}
