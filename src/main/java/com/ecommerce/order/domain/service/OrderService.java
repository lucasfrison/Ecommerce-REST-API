package com.ecommerce.order.domain.service;

import com.ecommerce.order.domain.entity.Order;

import java.util.List;

public interface OrderService {

    Order getById(String id);
    List<Order> getAll();
    String save(Order category);
    void remove(String id);
    void merge(Order category);
    List<Order> getByCustomerId(String customerId);

}
