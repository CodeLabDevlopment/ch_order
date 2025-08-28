package com.coffeehub.order_ms.application.usecases.order.item;

import com.coffeehub.order_ms.application.gateway.OrderItemGateway;
import com.coffeehub.order_ms.domain.model.OrderItem;

import java.util.UUID;

public record GetItemByIdCase(OrderItemGateway orderItemGateway) {

    public OrderItem execute(UUID itemId) {
        return orderItemGateway.findItemById(itemId);
    }

}
