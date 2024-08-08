package com.example.casestudymodule4.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SkuProductResponse {
    private Integer id;
    private Integer productId;
    private String color;
    private String size;
    private Integer price;
}
