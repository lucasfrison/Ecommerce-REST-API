package com.ecommerce.order.application.mapper;

import com.ecommerce.order.application.dto.OrderGenericDto;
import com.ecommerce.order.application.dto.OrderPersistRequestDto;
import com.ecommerce.order.domain.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface OrderMapper {

    OrderGenericDto orderToOrderDto(Order order);
    Order orderPostRequestDtoToOrder(OrderPersistRequestDto orderPersistRequestDto);

}
