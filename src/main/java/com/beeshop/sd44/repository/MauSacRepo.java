package com.beeshop.sd44.repository;

import com.beeshop.sd44.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MauSacRepo extends JpaRepository<MauSac, UUID> {
    Boolean existsByTen(String name);
}
