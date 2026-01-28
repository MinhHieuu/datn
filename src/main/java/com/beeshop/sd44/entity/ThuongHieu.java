package com.beeshop.sd44.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity
public class ThuongHieu {
    private UUID id;
    private String ten;
    @OneToMany(mappedBy = "thuongHieu")
    private List<SanPham> list;

}
