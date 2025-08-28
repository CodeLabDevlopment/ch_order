package com.coffeehub.order_ms.application.usecases.order.item;

import com.coffeehub.order_ms.application.gateway.OrderGateway;
import com.coffeehub.order_ms.application.gateway.OrderItemGateway;
import com.coffeehub.order_ms.domain.model.Order;

import java.util.UUID;

public record UpdateQuantityItemCase(OrderGateway orderGateway, OrderItemGateway orderItemGateway) {
    public void execute(UUID orderId, UUID itemId, Integer quantity) {
        Order order = orderGateway.findOrderById(orderId);

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        orderItemGateway.updateItemQuantity(order, itemId, quantity);
    }
}
