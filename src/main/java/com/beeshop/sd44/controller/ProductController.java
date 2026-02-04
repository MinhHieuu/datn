package com.beeshop.sd44.controller;

import com.beeshop.sd44.entity.ApiResponse;
import com.beeshop.sd44.entity.SanPham;
import com.beeshop.sd44.service.ChatLieuService;
import com.beeshop.sd44.service.ProductService;
import com.beeshop.sd44.service.ThuongHieuService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class ProductController {
    private final ProductService productService;
    private final ThuongHieuService thuongHieuService;
    private final ChatLieuService chatLieuService;

    public ProductController(ProductService productService,
                             ThuongHieuService thuongHieuService,
                             ChatLieuService chatLieuService) {
        this.productService = productService;
        this.thuongHieuService = thuongHieuService;
        this.chatLieuService = chatLieuService;
    }

    @GetMapping("san-pham")
    public ResponseEntity<?> getAllProduct() {
        List<SanPham> list = this.productService.getAll();
        return ResponseEntity.ok(new ApiResponse<>("lay thanh cong", list));
    }

//    @PostMapping("san-pham")
//    public ResponseEntity<?> createProduct(@Valid @RequestBody SanPham sanPham, BindingResult result,
//                                           MultipartFile file) {
//        List<FieldError> errors = result.getFieldErrors();
//        Boolean exitName = this.productService.isNameExit(sanPham.getTen());
//        if(result.hasErrors()) {
//            String errorMessages = errors.stream()
//                    .map(FieldError::getDefaultMessage)
//                    .collect(Collectors.joining(", "));
//            return ResponseEntity.badRequest().body(new ApiResponse<>(errorMessages, null));
//        }
//        if(exitName == true) {
//            return ResponseEntity.status(409).body(new ApiResponse<>("trung ten san pham", null));
//        }
//
//    }
}
