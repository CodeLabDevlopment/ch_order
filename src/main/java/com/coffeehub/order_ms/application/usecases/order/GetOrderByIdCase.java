package com.coffeehub.order_ms.application.usecases.order;

import com.coffeehub.order_ms.application.gateway.OrderGateway;
import com.coffeehub.order_ms.domain.model.Order;

import java.util.UUID;

public record GetOrderByIdCase(OrderGateway orderGateway) {

    public Order execute(UUID orderId) {
        return orderGateway.findOrderById(orderId);
    }

}
