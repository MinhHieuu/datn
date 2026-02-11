package com.beeshop.sd44.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "ten")
    private String name;
    private String email;
    @Column(name = "mat_khau")
    private String password;
    @Column(name = "vai_tro")
    private String role;
    @Column(name = "sdt")
    private String phone;
    @Column(name = "hinh_anh")
    private String avatar;
    @Column(name = "dia_chi")
    private String address;
    private Boolean deleteFlag;
    @OneToMany(mappedBy = "user")
    private List<Order> listOrder;
    @OneToOne(mappedBy = "user")
    private Cart cart;
    @OneToOne(mappedBy = "user")
    private Customer customer;
}
