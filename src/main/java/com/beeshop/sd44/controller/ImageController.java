package com.beeshop.sd44.controller;

import com.beeshop.sd44.dto.request.ImageRequest;
import com.beeshop.sd44.dto.response.ImageResponse;
import com.beeshop.sd44.entity.ApiResponse;
import com.beeshop.sd44.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<ImageResponse>>> getAll() {
        List<ImageResponse> list = imageService.getAllImage();
        return ResponseEntity.ok().body(new ApiResponse<>("lay thanh cong", list));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<List<ImageResponse>>> createImage(@RequestBody ImageRequest imageRequest){
        return ResponseEntity.status(201).
                body(new ApiResponse<>("tao moi thanh cong", imageService.handleSaveImage(imageRequest)));
    }
}
