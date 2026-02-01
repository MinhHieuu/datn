package com.beeshop.sd44.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;
import java.util.UUID;

@Entity
public class HoaDon {
    private UUID id;
    private String ma;
    private String ghiChu;
    private Date ngayTao;
    private Date ngayThanhToan;
    private Integer phiShip;
    private Double tongTien;
    private String PhuongThucThanhToan;
    private int trangThai;
    private int phanLoai;
    @ManyToOne
    @JoinColumn(name = "nguoi_dung_id")
    private NguoiDung nguoiDung;
    @ManyToOne
    @JoinColumn(name = "khach_hang_id")
    private KhachHang khachHang;
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;
}
