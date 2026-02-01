package com.beeshop.sd44.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class ThuongHieu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ten;
    @OneToMany(mappedBy = "thuongHieu")
    private List<SanPham> list;

}
