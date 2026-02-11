package com.beeshop.sd44.repository;

import com.beeshop.sd44.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BrandRepo extends JpaRepository<Brand, UUID> {
    Boolean existsByName(String name);
}
