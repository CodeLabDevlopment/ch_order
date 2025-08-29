package com.coffeehub.order_ms.application.usecases.order;

import com.coffeehub.order_ms.application.gateway.OrderGateway;
import com.coffeehub.order_ms.application.gateway.StatusHistoryGateway;
import com.coffeehub.order_ms.domain.dto.PaymentResponse;
import com.coffeehub.order_ms.domain.enums.OrderStatus;

import java.util.UUID;

public record OrderFinalizedCase(OrderGateway orderGateway, StatusHistoryGateway statusHistoryGateway) {

    public void execute(UUID orderId, PaymentResponse paymentResponse) {
        orderGateway.processFinalizedOrders(orderId, paymentResponse);
        orderGateway.updateOrderStatus(orderId, OrderStatus.RECEIVED);
        statusHistoryGateway.addStatusHistory(orderId, OrderStatus.RECEIVED);
    }

}
