package com.beeshop.sd44.service;

import com.beeshop.sd44.dto.response.MomoPaymentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;

import java.util.*;

public class MomoPaymentService {

    @Value("${momo.partnerCode}")
    private String partnerCode;

    @Value("${momo.accessKey}")
    private String accessKey;

    @Value("${momo.secretKey}")
    private String secretKey;

    @Value("${momo.endpoint}")
    private String endpoint;

    @Value("${momo.redirectUrl}")
    private String redirectUrl;

    @Value("${momo.ipnUrl}")
    private String ipnUrl;

    private final RestTemplate restTemplate;

    public MomoPaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    public MomoPaymentResponse createPaymentLink(String orderId, Long amount, String orderInfo) {
        try {
            String requestId = partnerCode + System.currentTimeMillis();
            String extraData = "";
            String requestType = "captureWallet";

            // Tạo raw signature
            String rawSignature = "accessKey=" + accessKey
                    + "&amount=" + amount
                    + "&extraData=" + extraData
                    + "&ipnUrl=" + ipnUrl
                    + "&orderId=" + orderId
                    + "&orderInfo=" + orderInfo
                    + "&partnerCode=" + partnerCode
                    + "&redirectUrl=" + redirectUrl
                    + "&requestId=" + requestId
                    + "&requestType=" + requestType;

            String signature = generateSignature(rawSignature, secretKey);

            Map<String, Object> requestBody = new LinkedHashMap<>();
            requestBody.put("partnerCode", partnerCode);
            requestBody.put("amount", amount.longValue());
            requestBody.put("orderId", orderId);
            requestBody.put("orderInfo", orderInfo);
            requestBody.put("requestId", requestId);
            requestBody.put("requestType", requestType);
            requestBody.put("signature", signature);
            requestBody.put("lang", "vi");
            requestBody.put("ipnUrl", ipnUrl);
            requestBody.put("redirectUrl", redirectUrl);
            requestBody.put("extraData", extraData);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            Map<String, Object> response = restTemplate.postForObject(endpoint, entity, Map.class);

            MomoPaymentResponse paymentResponse = new MomoPaymentResponse();
            paymentResponse.setPartnerCode((String) response.get("partnerCode"));
            paymentResponse.setRequestId((String) response.get("requestId"));
            paymentResponse.setOrderId((String) response.get("orderId"));
            paymentResponse.setMessage((String) response.get("message"));
            paymentResponse.setResultCode((Integer) response.get("resultCode"));
            paymentResponse.setPayUrl((String) response.get("payUrl"));
            paymentResponse.setDeeplink((String) response.get("deeplink"));
            paymentResponse.setQrCodeUrl((String) response.get("qrCodeUrl"));

            return paymentResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean verifyPayment(Map<String, String> payload) {
        try {
            String signature = payload.get("signature");
            String orderId    = (String) payload.get("orderId");
            String requestId  = (String) payload.get("requestId");
            long   amount     = Long.parseLong(payload.get("amount"));
            String orderInfo  = (String) payload.get("orderInfo");
            String orderType  = (String) payload.get("orderType");
            String transId    = payload.get("transId").toString();
            int resultCode = Integer.parseInt(payload.get("resultCode"));
            String message    = (String) payload.get("message");
            String payType    = (String) payload.get("payType");
            String responseTime = payload.get("responseTime").toString();
            String extraData  = (String) payload.get("extraData");
            System.out.println(extraData);
            String rawSignature = "accessKey=" + accessKey
                    + "&amount=" + amount
                    + "&extraData=" + extraData
                    + "&message=" + message
                    + "&orderId=" + orderId
                    + "&orderInfo=" + orderInfo
                    + "&orderType=" + orderType
                    + "&partnerCode=" + partnerCode
                    + "&payType=" + payType
                    + "&requestId=" + requestId
                    + "&responseTime=" + responseTime
                    + "&resultCode=" + resultCode
                    + "&transId=" + transId;

            String generatedSignature = generateSignature(rawSignature, secretKey);
            return signature.equals(generatedSignature);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String generateSignature(String data, String key) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
