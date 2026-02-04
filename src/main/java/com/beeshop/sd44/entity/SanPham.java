package com.beeshop.sd44.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "ten khong duoc de trong")
    private String ten;
    private Date ngay_tao;
    private Date ngay_sua;
    private String hinhAnh;
    @NotNull(message = "phai chon trang thai")
    private int trangThai;
    @NotNull(message = "phai chon chat lieu")
    @ManyToOne
    @JoinColumn(name = "chat_lieu_id")
    private ChatLieu chatLieu;
    @NotNull(message = "phai chon thuong hieu")
    @ManyToOne
    @JoinColumn(name = "thuong_hieu_id")
    private ThuongHieu thuongHieu;
    @OneToMany(mappedBy = "sanPham")
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

    public Date getNgay_tao() {
        return ngay_tao;
    }

    public void setNgay_tao(Date ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public Date getNgay_sua() {
        return ngay_sua;
    }

    public void setNgay_sua(Date ngay_sua) {
        this.ngay_sua = ngay_sua;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public ChatLieu getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(ChatLieu chatLieu) {
        this.chatLieu = chatLieu;
    }

    public ThuongHieu getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(ThuongHieu thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public List<SanPhamChiTiet> getList() {
        return list;
    }

    public void setList(List<SanPhamChiTiet> list) {
        this.list = list;
    }
}
