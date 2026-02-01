package com.beeshop.sd44.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int tongSanPham;
    private Date ngayTao;
    @OneToOne
    @JoinColumn(name = "nguoi_dung_id")
    private NguoiDung nguoiDung;
    @OneToMany(mappedBy = "gioHang")
    private List<GioHangChiTiet> listGioHangChiTiet;
}
