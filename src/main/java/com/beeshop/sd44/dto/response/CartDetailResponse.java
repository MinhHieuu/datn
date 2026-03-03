package com.beeshop.sd44.dto.response;

public class CartDetailResponse {
    private ProductDetailResponse productDetail;
    private Integer quantity;
    private Double totalPrice;

    public ProductDetailResponse getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetailResponse productDetail) {
        this.productDetail = productDetail;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
