package com.coffeehub.order_ms.infrastructure.mapper;

import com.coffeehub.order_ms.domain.model.Order;
import com.coffeehub.order_ms.domain.model.OrderItem;
import com.coffeehub.order_ms.domain.model.StatusHistory;
import com.coffeehub.order_ms.infrastructure.db.entity.OrderEntity;
import com.coffeehub.order_ms.infrastructure.db.entity.OrderItemEntity;
import com.coffeehub.order_ms.infrastructure.db.entity.StatusHistoryEntity;
import com.coffeehub.order_ms.infrastructure.web.response.item.ItemResponse;
import com.coffeehub.order_ms.infrastructure.web.response.order.OrderResponse;
import com.coffeehub.order_ms.infrastructure.web.response.statushistory.StatusHistoryResponse;

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
                                it.getProductDescription(),
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
                                it.productDescription(),
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
                                new OrderEntity(),
                                it.productId(),
                                it.productName(),
                                it.productDescription(),
                                it.price(),
                                it.quantity()
                        ))
                        .toList(),
                order.statusHistory().stream()
                        .map(it -> new StatusHistoryEntity(
                                it.id(),
                                it.status(),
                                new OrderEntity(),
                                it.changedAt()
                        ))
                        .toList(),
                order.createdAt(),
                order.updatedAt()
        );
    }

    public static OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.id().toString(),
                order.deliveryId() != null ? order.deliveryId().toString() : null,
                order.paymentId() != null ? order.paymentId().toString() : null,
                order.totalAmount(),
                order.status() != null ? order.status().name() : null,
                order.items().stream()
                        .map(it -> new ItemResponse(
                                it.id().toString(),
                                it.productId().toString(),
                                it.productName(),
                                it.price(),
                                it.quantity()
                        ))
                        .toList(),
                order.statusHistory().stream()
                        .map(it -> new StatusHistoryResponse(
                                it.id().toString(),
                                it.status().name(),
                                it.changedAt().toString()
                        ))
                        .toList(),
                order.createdAt().toString(),
                order.updatedAt().toString()
        );
    }

}
