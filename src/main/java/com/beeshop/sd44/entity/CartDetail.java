package com.beeshop.sd44.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "gio_hang_chi_tiet")
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "gia")
    private Double price;
    @Column(name = "so_luong")
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "gio_hang_id")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "san_pham_chi_tiet_id")
    private ProductDetail productDetail;
}
