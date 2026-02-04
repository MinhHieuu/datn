package com.beeshop.sd44.repository;

import com.beeshop.sd44.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductRepo extends JpaRepository<SanPham, UUID> {
    boolean existsByTen(String name);
}
