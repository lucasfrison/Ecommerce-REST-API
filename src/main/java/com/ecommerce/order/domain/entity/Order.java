package com.ecommerce.order.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {

    private String id;
    private String customerId;
    private Double total;
    private LocalDateTime orderDate;
    private PaymentStatus status;
    private List<OrderItem> orderItems;

}
