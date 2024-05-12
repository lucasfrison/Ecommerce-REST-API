package com.ecommerce.order.domain.entity;

import lombok.Data;

@Data
public class OrderItem {

    private String productId;
    private Integer quantity;
    private Double price;

}
