package com.beeshop.sd44.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ma;
    private String ten;
    private Integer loaiGiam;
    private Integer toiDa;
    private Integer trangThai;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    @OneToMany(mappedBy = "voucher")
    private List<HoaDon> listHoaDon;
}
