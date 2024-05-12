package com.ecommerce.order.application.controller;

import com.ecommerce.order.application.dto.OrderGenericDto;
import com.ecommerce.order.application.dto.OrderPersistRequestDto;
import com.ecommerce.order.application.mapper.OrderMapper;
import com.ecommerce.order.domain.entity.Order;
import com.ecommerce.order.domain.service.OrderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderController {

    @Inject
    OrderService orderService;

    @Inject
    OrderMapper orderMapper;

    @GET
    public Response getAllOrders() {
        try {
            List<OrderGenericDto> orders = orderService.getAll()
                    .stream()
                    .map(order -> orderMapper.orderToOrderDto(order))
                    .toList();
            return Response.ok(orders).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{id}")
    public Response getOrderById(@PathParam("id") String id) {
        try {
            Order order = orderService.getById(id);
            if (order == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(orderMapper.orderToOrderDto(order)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    public Response createOrder(OrderPersistRequestDto orderDto, @Context UriInfo uriInfo) {
        try {
            Order order = orderMapper.orderPostRequestDtoToOrder(orderDto);
            String orderGeneratedId = orderService.save(order);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(orderGeneratedId);
            return Response.created(uriBuilder.build()).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response updateOrder(@PathParam("id") String id, OrderPersistRequestDto requestDto) {
        try {
            Order order = orderService.getById(id);
            if (order == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            order.setOrderDate(requestDto.getOrderDate());
            order.setOrderItems(requestDto.getOrderItems());
            order.setTotal(requestDto.getTotal());
            order.setStatus(requestDto.getStatus());
            order.setCustomerId(requestDto.getCustomerId());
            orderService.merge(order);
            return Response.ok(orderMapper.orderToOrderDto(order)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteOrder(@PathParam("id") String id) {
        try {
            orderService.remove(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("customer/{id}")
    public Response getByCustomerId(@PathParam("id") String id) {
        try {
            List<OrderGenericDto> orders = orderService
                    .getByCustomerId(id)
                    .stream()
                    .map(order -> orderMapper.orderToOrderDto(order))
                    .toList();
            return Response.ok(orders).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}
