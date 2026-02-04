package com.beeshop.sd44.repository;

import com.beeshop.sd44.entity.ThuongHieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ThuongHieuRepo extends JpaRepository<ThuongHieu, UUID> {
    Boolean existsByTen(String name);
}
