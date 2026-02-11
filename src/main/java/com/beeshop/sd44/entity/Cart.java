package com.beeshop.sd44.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "gio_hang")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "tong_san_pham")
    private int sum;
    @Column(name = "ngay_tao")
    private Date CreatedAt;
    @OneToOne
    @JoinColumn(name = "nguoi_dung_id")
    private User user;
    @OneToMany(mappedBy = "cart")
    private List<CartDetail> listCartDetail;
}
