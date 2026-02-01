package com.beeshop.sd44.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class GioHangChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
