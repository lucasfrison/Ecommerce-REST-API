package com.ecommerce.product.application.dto;

import lombok.Data;

@Data
public class ProductPersistRequestDto  {

    private String name;
    private String description;
    private Double price;
    private String categoryId;

}
