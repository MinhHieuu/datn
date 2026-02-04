package com.beeshop.sd44.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


import java.util.List;
import java.util.UUID;

@Entity
public class ChatLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "ten khong duoc de trong")
    private String ten;
    @OneToMany(mappedBy = "chatLieu")
    private List<SanPham> list;

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

    public List<SanPham> getList() {
        return list;
    }

    public void setList(List<SanPham> list) {
        this.list = list;
    }
}
