package com.beeshop.sd44.dto.response;

import java.util.UUID;

public class ImageResponse {
    private UUID id;
    private String url;
    private String productDetailName;

    public UUID getId(){
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProductDetailName() {
        return productDetailName;
    }

    public void setProductDetailName(String productDetailName) {
        this.productDetailName = productDetailName;
    }
}
