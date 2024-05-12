package com.ecommerce.order.infrastructure.schema;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class OrderItemSchema {

    private ObjectId productId;
    private Integer quantity;
    private Double price;

}
