package com.coffeehub.order_ms.domain.model;

import com.coffeehub.order_ms.domain.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record Order(
        UUID id,
        UUID customerId,
        UUID paymentId,
        UUID deliveryId,
        BigDecimal totalAmount,
        OrderStatus status,
        List<OrderItem> items,
        List<StatusHistory> statusHistory,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}
