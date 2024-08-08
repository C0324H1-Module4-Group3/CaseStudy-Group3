package com.example.casestudymodule4.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
    private Integer id;
    private String name;
    private String imagePath;
    private Integer price;
    private Integer categoryId;
}
