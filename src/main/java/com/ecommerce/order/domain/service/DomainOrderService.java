package com.ecommerce.order.domain.service;

import com.ecommerce.order.domain.entity.Order;
import com.ecommerce.order.domain.repository.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DomainOrderService implements OrderService {

    @Inject
    OrderRepository orderRepository;

    @Override
    public Order getById(String id) {
       return orderRepository.getById(id);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    @Override
    public String save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void remove(String id) {
        orderRepository.remove(id);
    }

    @Override
    public void merge(Order order) {
        orderRepository.merge(order);
    }

    @Override
    public List<Order> getByCustomerId(String customerId) {
        return orderRepository.getByCustomerId(customerId);
    }

}
