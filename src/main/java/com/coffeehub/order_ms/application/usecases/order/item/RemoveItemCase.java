package com.coffeehub.order_ms.application.usecases.order.item;

import com.coffeehub.order_ms.application.gateway.OrderGateway;
import com.coffeehub.order_ms.application.gateway.OrderItemGateway;
import com.coffeehub.order_ms.domain.model.Order;

import java.util.UUID;

public record RemoveItemCase(OrderGateway orderGateway, OrderItemGateway orderItemGateway) {

    public void execute(UUID orderId, UUID itemId) {
        Order order = orderGateway.findOrderById(orderId);

        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }

        orderItemGateway.removeItemFromOrder(order, itemId);
    }

}
