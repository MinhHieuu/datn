package com.beeshop.sd44.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ten;
    private String email;
    private String matKhau;
    private String vaiTro;
    private String sdt;
    private String hinhAnh;
    private String diaChi;
    private boolean deleteFlag;
    @OneToMany(mappedBy = "nguoiDung")
    private List<HoaDon> listHoaDon;
    @OneToOne(mappedBy = "nguoiDung")
    private GioHang gioHang;
}
