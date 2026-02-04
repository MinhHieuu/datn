package com.beeshop.sd44.repository;

import com.beeshop.sd44.entity.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ChatLieuRepo extends JpaRepository<ChatLieu, UUID> {
    ChatLieu save(ChatLieu chatLieu);
    Boolean existsByTen(String name);
}
