package com.beeshop.sd44.controller;

import com.beeshop.sd44.dto.request.OrderRequest;
import com.beeshop.sd44.dto.response.OrderResponse;
import com.beeshop.sd44.dto.response.VNPayResponse;
import com.beeshop.sd44.entity.ApiResponse;
import com.beeshop.sd44.entity.Order;
import com.beeshop.sd44.service.OrderService;
import com.beeshop.sd44.service.VNPayService;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    // private final MomoPaymentService momoPaymentService;
    private final VNPayService vnPayService;

    public OrderController(OrderService orderService, VNPayService vnPayService) {
        this.orderService = orderService;
        // this.momoPaymentService = momoPaymentService;
        this.vnPayService = vnPayService;
    }

    @PostMapping("pay")
    public ResponseEntity<ApiResponse<Object>> handleOrder(@RequestBody OrderRequest orderRequset,
            Authentication authentication, HttpServletRequest request) throws ServletException, IOException {
        UUID userId = UUID.fromString(authentication.getName());
        OrderResponse orderResponse = orderService.hanldePlaceOrder(orderRequset, userId);
        if (orderRequset.getPaymentMethod().equals("COD")) {
            return ResponseEntity.ok().body(new ApiResponse<>("tao don hang thanh cong", orderResponse));
        } else {
            VNPayResponse vnPayResponse = this.vnPayService.createPaymentLink(orderResponse.getId().toString(),
                    orderRequset.getTotal().longValue(),
                    "Thanh toan don hang : " + orderResponse.getCode(), request);
            if (vnPayResponse == null || !vnPayResponse.isSuccess()) {
                return ResponseEntity.internalServerError()
                        .body(new ApiResponse<>("tao link thanh toan that bai", null));
            }
            return ResponseEntity.ok().body(new ApiResponse<>("tao link thanh toan thanh cong", vnPayResponse));
        }
    }

    @GetMapping("vnpay-return")
    public ResponseEntity<ApiResponse<?>> handleVNPayReturn(@RequestParam Map<String, String> params)
            throws IOException {
        System.out.println("VNPay return received: " + params);
        try {
            // Verify signature
            if (!vnPayService.verifyPayment(params)) {
                return ResponseEntity.badRequest().body(new ApiResponse<>("Chữ ký không hợp lệ", null));
            }

            String orderId = params.get("vnp_TxnRef");
            String responseCode = params.get("vnp_ResponseCode");

            if ("00".equals(responseCode)) {
                // Thanh toán thành công
                Order order = orderService.updateOrderStatus(UUID.fromString(orderId), 1);
                return ResponseEntity.ok().body(new ApiResponse<>("Thanh toán thành công", order));
            } else {
                // Thanh toán thất bại
                Order order = orderService.updateOrderStatus(UUID.fromString(orderId), 3); // Status 3: payment failed
                return ResponseEntity.ok().body(new ApiResponse<>("Thanh toán thất bại", order));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse<>("Lỗi xử lý callback", null));
        }
    }
    // MOMO đã die
    // @PostMapping("pay")
    // public ResponseEntity<ApiResponse<?>> handleOrder(@RequestBody OrderRequest
    // orderRequest,
    // Authentication authentication) {
    // UUID userId = UUID.fromString(authentication.getName());
    // OrderResponse orderResponse =
    // this.orderService.hanldePlaceOrder(orderRequest, userId);
    //
    // // Check payment method
    // if ("COD".equals(orderRequest.getPaymentMethod())) {
    // return ResponseEntity.ok().body(new ApiResponse<>("Đặt hàng thành công",
    // orderResponse));
    // } else if ("MOMO".equals(orderRequest.getPaymentMethod())) {
    // // Create Momo payment link
    // MomoPaymentResponse momoResponse = momoPaymentService.createPaymentLink(
    // orderResponse.getId(),
    // orderRequest.getTotal().longValue(),
    // "Thanh toan don hang " + orderResponse.getCode());
    //
    // if (momoResponse == null || momoResponse.getResultCode() != 0) {
    // return ResponseEntity.badRequest().body(new ApiResponse<>("Tạo link thanh
    // toán Momo thất bại", null));
    // }
    //
    // OrderPaymentResponse paymentResponse = new
    // OrderPaymentResponse(momoResponse);
    // return ResponseEntity.ok().body(new ApiResponse<>("Tạo link thanh toán Momo
    // thành công", paymentResponse));
    // } else {
    // return ResponseEntity.badRequest().body(new ApiResponse<>("Phương thức thanh
    // toán không hợp lệ", null));
    // }
    // }
    //
    // @PostMapping("momo-callback")
    // public ResponseEntity<ApiResponse<?>> handleMomoCallback(@RequestBody
    // Map<String, String> responseData) {
    // System.out.println("Momo callback received: " + responseData);
    // try {
    // // Verify signature
    // if (!momoPaymentService.verifyPayment(responseData)) {
    // return ResponseEntity.badRequest().body(new ApiResponse<>("Chữ ký không hợp
    // lệ", null));
    // }
    //
    // String orderId = responseData.get("orderId");
    // Integer resultCode = Integer.parseInt(responseData.getOrDefault("resultCode",
    // "1"));
    //
    // if (resultCode == 0) {
    // // Payment successful
    // Order order = orderService.updateOrderStatus(UUID.fromString(orderId), 1);
    // return ResponseEntity.ok().body(new ApiResponse<>("Thanh toán thành công",
    // order));
    // } else {
    // // Payment failed
    // Order order = orderService.updateOrderStatus(UUID.fromString(orderId), 3); //
    // Status 3: payment failed
    // return ResponseEntity.ok().body(new ApiResponse<>("Thanh toán thất bại",
    // order));
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // return ResponseEntity.badRequest().body(new ApiResponse<>("Lỗi xử lý
    // callback", null));
    // }
    // }
    //
    // @GetMapping("/return")
    // public ResponseEntity<String> handleMomoReturn(@RequestParam Map<String,
    // String> params) {
    // System.out.println("Momo return received: " + params);
    // // Xử lý kết quả thanh toán từ redirect
    // return ResponseEntity.ok("Thanh toán hoàn tất. Vui lòng kiểm tra đơn hàng.");
    // }

}
