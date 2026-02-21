package com.beeshop.sd44.controller;

import com.beeshop.sd44.dto.request.ProductDetailRequest;
import com.beeshop.sd44.dto.response.ProductDetailResponse;
import com.beeshop.sd44.entity.*;
import com.beeshop.sd44.service.ImageService;
import com.beeshop.sd44.service.ProductDetailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/product-detail")
public class ProductDetailController {
    private final ProductDetailService productDetailService;
    private final ImageService imageService;
    public ProductDetailController(ProductDetailService productDetailService, ImageService imageService) {
        this.productDetailService = productDetailService;
        this.imageService = imageService;
    }
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<ProductDetailResponse>>> getListProductDetail() {
        List<ProductDetailResponse> list = this.productDetailService.getListDetail(false);
        return ResponseEntity.ok().body(new ApiResponse<>("lay thanh cong", list));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDetailResponse>> createProduct(@Valid @RequestBody ProductDetailRequest detail,
                                                                    BindingResult result) {
        List<FieldError> errors = result.getFieldErrors();
        Boolean exitName = this.productDetailService.isNameExit(detail.getName());
        if(result.hasErrors()) {
            String errorMessages = errors.stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(new ApiResponse<>(errorMessages, null));
        }
        if(exitName == true) {
            return ResponseEntity.status(409).body(new ApiResponse<>("ten da ton tai", null));
        }
        Color color = new Color(detail.getColorId());
        Size size = new Size(detail.getSizeId());
        Product product = new Product();
        product.setId(detail.getProductId());
        Boolean exitsProduct = this.productDetailService.isProductExit(product, color, size);
        if(exitsProduct) {
            return ResponseEntity.status(409).body(new ApiResponse<>("san pham da ton tai", null));
        }
        ProductDetailResponse productDetail = this.productDetailService.createProductDetail(detail);
        return ResponseEntity.status(201).body(new ApiResponse<>("tao moi thanh cong", productDetail));
    }


}
