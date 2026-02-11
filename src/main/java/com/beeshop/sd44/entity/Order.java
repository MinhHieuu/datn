package com.beeshop.sd44.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "hoa_don")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "ma")
    private String code;
    @Column(name = "ghi_chu")
    private String note;
    @Column(name = "ngay_tao")
    private Date createdAt;
    @Column(name = "ngay_thanh_toan")
    private Date paymentDate;
    @Column(name = "phi_ship")
    private Integer shippingFee;
    @Column(name = "tong_tien")
    private Double total;
    @Column(name = "phuong_thuc_thanh_toan")
    private String paymentMethod;
    @Column(name = "trang_thai")
    private Integer status;
    @Column(name = "phan_loai")
    private Integer type;
    @ManyToOne
    @JoinColumn(name = "nguoi_dung_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "khach_hang_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> detailList;
}
