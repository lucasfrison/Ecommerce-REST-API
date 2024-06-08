package com.ecommerce.product.domain.entity;

import lombok.*;

import java.util.List;

@Data
public class Product {

    private String id;
    private String name;
    private String description;
    private Double price;
    private String categoryId;
    private List<String> images;

}
