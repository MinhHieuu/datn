package com.beeshop.sd44.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity
public class SanPhamChiTiet {
    private UUID id;
    private String ten;
    private String moTa;
    private Integer soLuong;
    private Double giaNhap;
    private Double giaBan;
    private boolean deleteFlag;
    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    private SanPham sanPham;
    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;
    @ManyToOne
    @JoinColumn(name = "mau_sac_id")
    private MauSac mauSac;
    @OneToMany(mappedBy = "sanPhamChiTiet")
    private List<Image> imageList;
    @OneToMany(mappedBy = "san_pham_chi_tiet_id")
    private List<GioHangChiTiet> listGioHangChiTiet;
}
