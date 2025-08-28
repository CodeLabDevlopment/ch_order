package com.coffeehub.order_ms.domain.model;

import com.coffeehub.order_ms.domain.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record StatusHistory(
        UUID id,
        OrderStatus status,
        Order order,
        LocalDateTime changedAt
) {

}
