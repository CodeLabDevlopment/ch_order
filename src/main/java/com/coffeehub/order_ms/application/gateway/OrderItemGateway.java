package com.coffeehub.order_ms.application.gateway;

import com.coffeehub.order_ms.domain.model.Order;
import com.coffeehub.order_ms.domain.model.OrderItem;

import java.util.UUID;

public interface OrderItemGateway {

    void addItemToOrder(Order order, OrderItem item);
    void removeItemFromOrder(Order order, UUID itemId);
    void updateItemQuantity(Order order, UUID itemId, Integer quantity);
    OrderItem findItemById(UUID itemId);

}
