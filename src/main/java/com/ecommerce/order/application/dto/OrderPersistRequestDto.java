package com.ecommerce.order.application.dto;

import com.ecommerce.order.domain.entity.OrderItem;
import com.ecommerce.order.domain.entity.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderPersistRequestDto {

    private String customerId;
    private Double total;
    private LocalDateTime orderDate;
    private PaymentStatus status;
    private List<OrderItem> orderItems;

}
