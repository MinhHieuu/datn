package com.beeshop.sd44.dto.request;

import java.util.UUID;

public class ImageRequest {
    private String[] url;
    private UUID productDetailId;

    public String[] getUrl() {
        return url;
    }

    public void setUrl(String[] url) {
        this.url = url;
    }

    public UUID getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(UUID productDetailId) {
        this.productDetailId = productDetailId;
    }
}
