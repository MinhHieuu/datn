package com.beeshop.sd44.controller;

import com.beeshop.sd44.dto.response.ProductDetailResponse;
import com.beeshop.sd44.entity.ApiResponse;
import com.beeshop.sd44.service.ProductDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/product-detail")
public class ProductDetailController {
    private final ProductDetailService productDetailService;
    public ProductDetailController(ProductDetailService productDetailService) {
        this.productDetailService = productDetailService;
    }
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<ProductDetailResponse>>> getProductDetail() {
        List<ProductDetailResponse> list = this.productDetailService.getAll();
        return ResponseEntity.ok().body(new ApiResponse<>("lay thanh cong", list));
    }
}
