package com.ecommerce.order.infrastructure.repository;

import com.ecommerce.order.domain.entity.Order;
import com.ecommerce.order.domain.repository.OrderRepository;
import com.ecommerce.order.infrastructure.mapper.OrderSchemaMapper;
import com.ecommerce.order.infrastructure.schema.OrderSchema;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class MongoDBOrderRepository implements OrderRepository, PanacheMongoRepositoryBase<OrderSchema, ObjectId> {

    @Inject
    OrderSchemaMapper orderSchemaMapper;

    @Override
    public Order getById(String id) {
        OrderSchema orderSchema = findById(new ObjectId(id));
        return orderSchemaMapper.orderSchemaToOrder(orderSchema);
    }

    @Override
    public List<Order> getAll() {
        return findAll()
                .stream()
                .map(orderSchemaMapper::orderSchemaToOrder)
                .toList();
    }

    @Override
    public String save(Order order) {
        OrderSchema orderSchema = orderSchemaMapper.orderToOrderSchema(order);
        persist(orderSchema);
        return orderSchema.getId().toString();
    }

    @Override
    public void remove(String id) {
        deleteById(new ObjectId(id));
    }

    @Override
    public void merge(Order order) {
        OrderSchema orderSchema = orderSchemaMapper.orderToOrderSchema(order);
        update(orderSchema);
    }

    @Override
    public List<Order> getByCustomerId(String customerId) {
        List<OrderSchema> orders = find("customerId", new ObjectId(customerId)).list();
        return orders
                .stream()
                .map(orderSchema -> orderSchemaMapper.orderSchemaToOrder(orderSchema))
                .toList();
    }

}
