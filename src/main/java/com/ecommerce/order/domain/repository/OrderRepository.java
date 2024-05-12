package com.ecommerce.order.domain.repository;

import com.ecommerce.order.domain.entity.Order;

import java.util.List;

public interface OrderRepository {

    Order getById(String id);
    List<Order> getAll();
    String save(Order category);
    void remove(String id);
    void merge(Order category);
    List<Order> getByCustomerId(String customerId);

}
