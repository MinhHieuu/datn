package com.beeshop.sd44.controller;

import com.beeshop.sd44.entity.ApiResponse;
import com.beeshop.sd44.entity.ChatLieu;
import com.beeshop.sd44.entity.MauSac;
import com.beeshop.sd44.service.MauSacService;
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
public class MauSacController {
    @Autowired
    private MauSacService service;
    @GetMapping("mau-sac")
    public ResponseEntity<?> getAll(Model model){
        List<MauSac> list = this.service.getAll();
        return ResponseEntity.ok().body(new ApiResponse<>("lay thanh cong", list));
    }

    @PostMapping("mau-sac")
    public ResponseEntity<?> create(@Valid @RequestBody MauSac mauSac, BindingResult result) {
        boolean exitsMau = this.service.isNameExit(mauSac.getTen());
        if(exitsMau == true) {
            return ResponseEntity.status(409).body(new ApiResponse<ChatLieu>("da ton tai", null));
        }
        if(result.hasErrors()) {
            String error = result.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse<>(error, null));
        }
        this.service.hanldeSave(mauSac);
        return ResponseEntity.status(201).body(new ApiResponse<>("tao moi thanh cong", mauSac));
    }

    @DeleteMapping("mau-sac/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") UUID id) {
        MauSac mauSac = this.service.getById(id);
        if(mauSac == null) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("khong tim thay", null));
        }
        this.service.handleDelete(mauSac);
        return ResponseEntity.ok().body(new ApiResponse<>("xoa thanh cong", null));
    }

    @PutMapping("mau-sac/{id}")
    public ResponseEntity<?> update (@PathVariable("id")UUID id,
                                     @Valid @RequestBody MauSac newMauSac, BindingResult result) {
        MauSac mauSac = this.service.getById(id);
        if (mauSac == null) {
            return ResponseEntity.status(404).body(new ApiResponse<>("khong tim thay", null));
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(result.getFieldError().getDefaultMessage(), null));
        }
        mauSac.setTen(newMauSac.getTen());
        this.service.hanldeSave(mauSac);
        return ResponseEntity.ok().body(new ApiResponse<>("cap nhat thanh cong", mauSac));
    }
}
