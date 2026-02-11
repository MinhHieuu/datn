package com.beeshop.sd44.dto.request;

import java.util.UUID;

public class ProductRequest {
    private UUID id;
    private String name;
    private String image;
    private Integer status;
    private UUID MarterialId;
    private UUID BrandId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UUID getMarterialId() {
        return MarterialId;
    }

    public void setMarterialId(UUID marterialId) {
        MarterialId = marterialId;
    }

    public UUID getBrandId() {
        return BrandId;
    }

    public void setBrandId(UUID brandId) {
        BrandId = brandId;
    }
}
