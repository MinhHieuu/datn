package com.beeshop.sd44.service;

import com.beeshop.sd44.dto.response.ProductDetailResponse;
import com.beeshop.sd44.entity.ProductDetail;
import com.beeshop.sd44.repository.ProductDetailRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailService {
    private final ProductDetailRepo productDetailRepo;
    public ProductDetailService (ProductDetailRepo productDetailRepo) {
        this.productDetailRepo = productDetailRepo;
    }

    public List<ProductDetailResponse> getAll() {
        List<ProductDetail> detailList = this.productDetailRepo.findAll();
        List<ProductDetailResponse> responseList = new ArrayList<>();
        for(ProductDetail detail : detailList) {
            responseList.add(buildResponse(detail));
        }
        return responseList;
    }

    public ProductDetailResponse buildResponse(ProductDetail detail) {
        ProductDetailResponse response = new ProductDetailResponse();
        response.setId(detail.getId());
        response.setName(detail.getName());
        response.setQuantity(detail.getQuantity());
        response.setCostPrice(detail.getCostPrice());
        response.setSalePrice(detail.getSalePrice());
        response.setSizeId(detail.getSize().getId());
        response.setColorId(detail.getColor().getId());
        response.setImages(detail.getImages());
        return  response;
    }
}
