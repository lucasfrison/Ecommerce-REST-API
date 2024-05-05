package com.ecommerce.product.domain.entity;

import lombok.*;

@Data
public class Product {

    private String id;
    private String name;
    private String description;
    private Double price;
    private String categoryId;

}
