package com.beeshop.sd44.controller;

import com.beeshop.sd44.entity.ApiResponse;
import com.beeshop.sd44.entity.ChatLieu;
import com.beeshop.sd44.entity.ThuongHieu;
import com.beeshop.sd44.service.ThuongHieuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class ThuongHieuController {
    @Autowired
    private ThuongHieuService service;
    @GetMapping("thuong-hieu")
    public ResponseEntity<?> getAll(Model model){
        List<ThuongHieu> list = this.service.getAll();
        return ResponseEntity.ok().body(new ApiResponse<>("lay thanh cong", list));
    }

    @PostMapping("thuong-hieu")
    public ResponseEntity<?> create(@Valid @RequestBody ThuongHieu thuongHieu, BindingResult result) {
        boolean exitsThuongHieu = this.service.isNameExit(thuongHieu.getTen());
        if(exitsThuongHieu == true) {
            return ResponseEntity.status(409).body(new ApiResponse<ChatLieu>("da ton tai", null));
        }
        if(result.hasErrors()) {
            String error = result.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse<>(error, null));
        }
        this.service.hanldeSave(thuongHieu);
        return ResponseEntity.status(201).body(new ApiResponse<>("tao moi thanh cong", thuongHieu));
    }

    @DeleteMapping("thuong-hieu/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") UUID id) {
        ThuongHieu thuongHieu = this.service.getById(id);
        if(thuongHieu == null) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("khong tim thay", null));
        }
        this.service.handleDelete(thuongHieu);
        return ResponseEntity.ok().body(new ApiResponse<>("xoa thanh cong", null));
    }

    @PutMapping("thuong-hieu/{id}")
    public ResponseEntity<?> update (@PathVariable("id")UUID id,
                                     @Valid @RequestBody ThuongHieu newThuongHieu, BindingResult result) {
        ThuongHieu thuongHieu = this.service.getById(id);
        if (thuongHieu == null) {
            return ResponseEntity.status(404).body(new ApiResponse<>("khong tim thay", null));
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(result.getFieldError().getDefaultMessage(), null));
        }
        thuongHieu.setTen(newThuongHieu.getTen());
        this.service.hanldeSave(thuongHieu);
        return ResponseEntity.ok().body(new ApiResponse<>("cap nhat thanh cong", thuongHieu));
    }
}
