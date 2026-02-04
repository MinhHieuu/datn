package com.beeshop.sd44.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

@Entity
public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "ten khong duoc de trong")
    private String ten;
    @OneToMany(mappedBy = "mauSac")
    private List<SanPhamChiTiet> list;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public List<SanPhamChiTiet> getList() {
        return list;
    }

    public void setList(List<SanPhamChiTiet> list) {
        this.list = list;
    }
}
