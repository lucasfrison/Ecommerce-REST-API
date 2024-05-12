package com.ecommerce.order.infrastructure.mapper;

import com.ecommerce.order.domain.entity.Order;
import com.ecommerce.order.domain.entity.OrderItem;
import com.ecommerce.order.domain.entity.PaymentStatus;
import com.ecommerce.order.infrastructure.schema.OrderItemSchema;
import com.ecommerce.order.infrastructure.schema.OrderSchema;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface OrderSchemaMapper {

    Order orderSchemaToOrder(OrderSchema orderSchema);
    OrderSchema orderToOrderSchema(Order order);

    default String fromObjectId(ObjectId id) {
        return id == null ? "" : id.toString();
    }

    default ObjectId toObjectId(String id) {
        return id == null ? null : new ObjectId(id);
    }

    default OrderItemSchema toOrderItemSchema(OrderItem orderItem) {
        OrderItemSchema orderItemSchema = new OrderItemSchema();
        orderItemSchema.setProductId(new ObjectId(orderItem.getProductId()));
        orderItemSchema.setQuantity(orderItem.getQuantity());
        orderItemSchema.setPrice(orderItem.getPrice());
        return orderItemSchema;
    }

    default OrderItem toOrderItem(OrderItemSchema orderItemSchema) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(orderItemSchema.getProductId().toString());
        orderItem.setQuantity(orderItemSchema.getQuantity());
        orderItem.setPrice(orderItemSchema.getPrice());
        return orderItem;
    }

    default PaymentStatus toPaymentStatus(String paymentStatus) {
        return paymentStatus == null ? null : PaymentStatus.valueOf(paymentStatus);
    }

    default String fromPaymentStatus(PaymentStatus paymentStatus) {
        return paymentStatus == null ? "" : paymentStatus.toString();
    }

}
