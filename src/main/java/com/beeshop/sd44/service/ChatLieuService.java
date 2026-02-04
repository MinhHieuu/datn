package com.beeshop.sd44.service;

import com.beeshop.sd44.entity.ChatLieu;
import com.beeshop.sd44.repository.ChatLieuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatLieuService {
    @Autowired
    private ChatLieuRepo repo;

    public ChatLieu hanldeSave(ChatLieu chatLieu) {
       return this.repo.save(chatLieu);
    }

    public List<ChatLieu> getAll() {
        return this.repo.findAll();
    }

    public ChatLieu getById(UUID id){
        Optional<ChatLieu> chatLieu = this.repo.findById(id);
        if(chatLieu.isPresent()) {
            return chatLieu.get();
        }
        return null;
    }

    public void handleDelete(ChatLieu chatLieu) {
        this.repo.delete(chatLieu);
    }

    public boolean isNameExit(String name) {
        return repo.existsByTen(name);
    }
}
