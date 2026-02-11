package com.beeshop.sd44.service;

import com.beeshop.sd44.entity.Marterial;
import com.beeshop.sd44.repository.MaterialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MarterialService {
    @Autowired
    private MaterialRepo repo;

    public Marterial hanldeSave(Marterial marterial) {
       return this.repo.save(marterial);
    }

    public List<Marterial> getAll() {
        return this.repo.findAll();
    }

    public Marterial getById(UUID id){
        Optional<Marterial> chatLieu = this.repo.findById(id);
        if(chatLieu.isPresent()) {
            return chatLieu.get();
        }
        return null;
    }

    public void handleDelete(Marterial marterial) {
        this.repo.delete(marterial);
    }

    public boolean isNameExit(String name) {
        return repo.existsByName(name);
    }
}
