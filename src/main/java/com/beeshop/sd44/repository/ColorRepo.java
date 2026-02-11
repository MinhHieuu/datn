package com.beeshop.sd44.repository;

import com.beeshop.sd44.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ColorRepo extends JpaRepository<Color, UUID> {
    Boolean existsByName(String name);
}
