package com.beeshop.sd44.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity
public class GioHangChiTiet {
    private UUID id;
    private Double gia;
    private Integer soLuong;
    @ManyToOne
    @JoinColumn(name = "gio_hang_id")
    private GioHang gioHang;
    @ManyToOne
    @JoinColumn(name = "san_pham_chi_tiet_id")
    private SanPhamChiTiet sanPhamChiTiet;
}
