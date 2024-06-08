package com.grt.order.entity;

import com.grt.order.controller.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest orderRequest) {
        return Order.builder()
                .id(orderRequest.id())
                .reference(orderRequest.reference())
                .paymentMethod(orderRequest.paymentMethod())
                .customerId(orderRequest.customerId())
                .build();
    }

    public OrderResponse  fromOrder(Order order) {
        return new OrderResponse(order.getId(), order.getReference(), order.getTotalAmount(),
                order.getPaymentMethod(), order.getCustomerId());
    }
}
