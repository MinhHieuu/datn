package com.beeshop.sd44.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beeshop.sd44.entity.CartDetail;
import com.beeshop.sd44.entity.Cart;

@Repository
public interface CartDetailRepo extends JpaRepository<CartDetail, UUID> {
    List<CartDetail> findByCart(Cart cart);
}
