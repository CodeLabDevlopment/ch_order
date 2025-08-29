package com.coffeehub.order_ms.application.usecases.order;

import com.coffeehub.order_ms.application.gateway.OrderGateway;
import com.coffeehub.order_ms.application.gateway.StatusHistoryGateway;
import com.coffeehub.order_ms.domain.enums.OrderStatus;

import java.util.UUID;

public record UpdateOrderStatusCase(OrderGateway orderGateway, StatusHistoryGateway statusHistoryGateway) {

    public void execute(UUID orderId, OrderStatus status) {
        orderGateway.updateOrderStatus(orderId, status);
        statusHistoryGateway.addStatusHistory(orderId, status);
    }

}
