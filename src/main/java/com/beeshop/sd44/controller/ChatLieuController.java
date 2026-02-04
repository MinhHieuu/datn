package com.beeshop.sd44.controller;

import com.beeshop.sd44.entity.ApiResponse;
import com.beeshop.sd44.entity.ChatLieu;
import com.beeshop.sd44.service.ChatLieuService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class ChatLieuController {
    private final ChatLieuService chatLieuService;

    public ChatLieuController(ChatLieuService chatLieuService) {
        this.chatLieuService = chatLieuService;
    }

    @GetMapping("chat-lieu")
    public ResponseEntity<List<ChatLieu>> getAll(Model model){
       List<ChatLieu> list = this.chatLieuService.getAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("chat-lieu")
    public ResponseEntity<ApiResponse<ChatLieu>> create(@Valid @RequestBody ChatLieu chatLieu, BindingResult result) {
        boolean exitsChatLieu = this.chatLieuService.isNameExit(chatLieu.getTen());
        if(exitsChatLieu == true) {
            return ResponseEntity.status(409).body(new ApiResponse<ChatLieu>("da ton tai", null));
        }
        if(result.hasErrors()) {
            String error = result.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse<>(error, null));
        }
        this.chatLieuService.hanldeSave(chatLieu);
        return ResponseEntity.status(201).body(new ApiResponse<>("tao moi thanh cong", chatLieu));
    }

    @DeleteMapping("/chat-lieu/{id}")
    public ResponseEntity<?> delete (@PathVariable("id")UUID id) {
        ChatLieu chatLieu = this.chatLieuService.getById(id);
        if(chatLieu == null) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("khong tim thay", null));
        }
        this.chatLieuService.handleDelete(chatLieu);
        return ResponseEntity.ok().body(new ApiResponse<>("xoa thanh cong", null));
    }

    @PutMapping("chat-lieu/{id}")
    public ResponseEntity<?> update (@PathVariable("id")UUID id,
                                     @Valid @RequestBody ChatLieu newChatLieu, BindingResult result) {
        ChatLieu chatLieu = this.chatLieuService.getById(id);
        if(chatLieu == null) {
            return ResponseEntity.status(404).body(new ApiResponse<>("khong tim thay", null));
        }
        if(result.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(result.getFieldError().getDefaultMessage(), null));
        }
        chatLieu.setTen(newChatLieu.getTen());
        this.chatLieuService.hanldeSave(chatLieu);
        return ResponseEntity.ok().body(new ApiResponse<>("cap nhat thanh cong", newChatLieu));
    }
}
