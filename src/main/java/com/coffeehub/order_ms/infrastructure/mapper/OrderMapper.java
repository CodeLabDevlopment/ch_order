package com.coffeehub.order_ms.infrastructure.mapper;

import com.coffeehub.order_ms.domain.model.Order;
import com.coffeehub.order_ms.domain.model.OrderItem;
import com.coffeehub.order_ms.domain.model.StatusHistory;
import com.coffeehub.order_ms.infrastructure.db.entity.OrderEntity;
import com.coffeehub.order_ms.infrastructure.db.entity.OrderItemEntity;
import com.coffeehub.order_ms.infrastructure.db.entity.StatusHistoryEntity;

import java.util.UUID;

public final class OrderMapper {

    public static Order toDomain(OrderEntity entity) {
        return new Order(
                entity.getId(),
                entity.getCustomerId(),
                entity.getPaymentId(),
                entity.getDeliveryId(),
                entity.getTotalAmount(),
                entity.getStatus(),
                entity.getItems().stream()
                        .map(it -> new OrderItem(
                                it.getId(),
                                new Order(
                                        entity.getId(),
                                        entity.getCustomerId(),
                                        entity.getPaymentId(),
                                        entity.getDeliveryId(),
                                        entity.getTotalAmount(),
                                        entity.getStatus(),
                                        null,
                                        null,
                                        entity.getCreatedAt(),
                                        entity.getUpdatedAt()
                                ),
                                it.getProductId(),
                                it.getProductName(),
                                it.getPrice(),
                                it.getQuantity()
                        ))
                        .toList(),
                entity.getStatusHistory().stream()
                        .map(it -> new StatusHistory(
                                it.getId(),
                                it.getStatus(),
                                null,
                                it.getChangedAt()
                        ))
                        .toList(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static OrderEntity toEntity(UUID customerId, Order order) {
        return new OrderEntity(
                null,
                customerId,
                order.paymentId(),
                order.deliveryId(),
                order.totalAmount(),
                order.status(),
                order.items().stream()
                        .map(it -> new OrderItemEntity(
                                null,
                                null,
                                it.productId(),
                                it.productName(),
                                it.price(),
                                it.quantity()
                        ))
                        .toList(),
                order.statusHistory().stream()
                        .map(it -> new StatusHistoryEntity(
                                null,
                                it.status(),
                                null,
                                it.changedAt()
                        ))
                        .toList(),
                order.createdAt(),
                order.updatedAt()
        );
    }

    public static OrderEntity toEntity(Order order) {
        return new OrderEntity(
                order.id(),
                order.customerId(),
                order.paymentId(),
                order.deliveryId(),
                order.totalAmount(),
                order.status(),
                order.items().stream()
                        .map(it -> new OrderItemEntity(
                                it.id(),
                                null,
                                it.productId(),
                                it.productName(),
                                it.price(),
                                it.quantity()
                        ))
                        .toList(),
                order.statusHistory().stream()
                        .map(it -> new StatusHistoryEntity(
                                it.id(),
                                it.status(),
                                null,
                                it.changedAt()
                        ))
                        .toList(),
                order.createdAt(),
                order.updatedAt()
        );
    }

}
