package com.beeshop.sd44.repository;


import com.beeshop.sd44.entity.Color;
import com.beeshop.sd44.entity.Product;
import com.beeshop.sd44.entity.ProductDetail;
import com.beeshop.sd44.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ProductDetailRepo extends JpaRepository<ProductDetail, UUID> {
    List<ProductDetail> getProductDetailByDeleteFlag(boolean deleteFlag);
    List<ProductDetail> getProductDetailByName(String name);
    Boolean existsByName(String name);
    Boolean existsByProductAndColorAndSize(Product product, Color color, Size size);
}
