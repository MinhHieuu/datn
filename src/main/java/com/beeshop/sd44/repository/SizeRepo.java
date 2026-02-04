package com.beeshop.sd44.repository;

import com.beeshop.sd44.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SizeRepo extends JpaRepository<Size, UUID> {
    Boolean existsByTen(String name);
}
