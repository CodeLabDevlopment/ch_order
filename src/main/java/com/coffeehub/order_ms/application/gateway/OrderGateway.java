package com.coffeehub.order_ms.application.gateway;

import com.coffeehub.order_ms.domain.enums.OrderStatus;
import com.coffeehub.order_ms.domain.model.Order;

import java.util.UUID;

public interface OrderGateway {

    Order createOrder(UUID customerId, Order order);
    void updateOrderStatus(UUID orderId, OrderStatus status);
    Order findOrderById(UUID orderId);

}
