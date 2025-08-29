package com.coffeehub.order_ms.application.gateway;

import com.coffeehub.order_ms.domain.enums.OrderStatus;

import java.util.UUID;

public interface StatusHistoryGateway {

    void addStatusHistory(UUID orderId, OrderStatus status);

}
