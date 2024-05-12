package com.ecommerce.order.infrastructure.schema;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@MongoEntity(collection = "Orders")
@Getter
@Setter
public class OrderSchema extends PanacheMongoEntity {

    private ObjectId id;
    private ObjectId customerId;
    private Double total;
    private LocalDateTime orderDate;
    private String status;
    private List<OrderItemSchema> orderItems;

}
