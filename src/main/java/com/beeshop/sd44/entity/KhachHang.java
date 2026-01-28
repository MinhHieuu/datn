package com.beeshop.sd44.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class KhachHang {
    private UUID id;
    private String ten;
    private String sdt;
    private Date ngayTao;
    private String diaChi;
    @OneToMany(mappedBy = "khachHang")
    private List<HoaDon> listHoaDon;
}
