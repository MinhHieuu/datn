package com.beeshop.sd44.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "khach_hang")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ten;
    private String sdt;
    private Date ngayTao;
    private String diaChi;
    @OneToMany(mappedBy = "customer")
    private List<Order> listOrder;
    @OneToOne
    @JoinColumn(name = "nguoi_dung_id")
    private User user;
}
