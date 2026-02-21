package com.beeshop.sd44.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String url;
    @ManyToOne
    @JoinColumn(name = "image_id")
    private ProductDetail productDetail;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

   public Image(String url, ProductDetail detail) {
        this.url = url;
        this.productDetail = detail;
   }

   public Image(){};
}
