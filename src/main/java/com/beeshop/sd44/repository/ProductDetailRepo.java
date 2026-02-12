package com.beeshop.sd44.repository;


import com.beeshop.sd44.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductDetailRepo extends JpaRepository<ProductDetail, UUID> {
}
