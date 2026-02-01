package com.beeshop.sd44.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ten;
    private Date ngay_tao;
    private Date ngay_sua;
    private String hinhAnh;
    private int trangThai;
    @ManyToOne
    @JoinColumn(name = "chat_lieu_id")
    private ChatLieu chatLieu;
    @ManyToOne
    @JoinColumn(name = "thuong_hieu_id")
    private ThuongHieu thuongHieu;
    @OneToMany(mappedBy = "sanPham")
    private List<SanPhamChiTiet> list;

}
