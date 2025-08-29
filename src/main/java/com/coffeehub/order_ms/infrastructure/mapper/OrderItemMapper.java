package com.coffeehub.order_ms.infrastructure.mapper;

import com.coffeehub.order_ms.domain.model.Order;
import com.coffeehub.order_ms.domain.model.OrderItem;
import com.coffeehub.order_ms.infrastructure.db.entity.OrderEntity;
import com.coffeehub.order_ms.infrastructure.db.entity.OrderItemEntity;

public final class OrderItemMapper {

    public static OrderItem toDomain(OrderItemEntity orderItemEntity) {
        return new OrderItem(
                orderItemEntity.getId(),
                new Order(
                        orderItemEntity.getOrder().getId(),
                        orderItemEntity.getOrder().getCustomerId(),
                        orderItemEntity.getOrder().getPaymentId(),
                        orderItemEntity.getOrder().getDeliveryId(),
                        orderItemEntity.getOrder().getTotalAmount(),
                        orderItemEntity.getOrder().getStatus(),
                        null,
                        null,
                        orderItemEntity.getOrder().getCreatedAt(),
                        orderItemEntity.getOrder().getUpdatedAt()
                ),
                orderItemEntity.getProductId(),
                orderItemEntity.getProductName(),
                orderItemEntity.getProductDescription(),
                orderItemEntity.getPrice(),
                orderItemEntity.getQuantity()
        );
    }

    public static OrderItemEntity toEntity(Order order, OrderItem item) {
        return new OrderItemEntity(
                item.id(),
                new OrderEntity(
                        order.id(),
                        order.customerId(),
                        order.paymentId(),
                        order.deliveryId(),
                        order.totalAmount(),
                        order.status(),
                        null,
                        null,
                        order.createdAt(),
                        order.updatedAt()
                ),
                item.productId(),
                item.productName(),
                item.productDescription(),
                item.price(),
                item.quantity()
        );
    }

    public static OrderItemEntity toEntity(OrderItem orderItem) {
        return new OrderItemEntity(
                orderItem.id(),
                new OrderEntity(
                        orderItem.order().id(),
                        orderItem.order().customerId(),
                        orderItem.order().paymentId(),
                        orderItem.order().deliveryId(),
                        orderItem.order().totalAmount(),
                        orderItem.order().status(),
                        null,
                        null,
                        orderItem.order().createdAt(),
                        orderItem.order().updatedAt()
                ),
                orderItem.productId(),
                orderItem.productName(),
                orderItem.productDescription(),
                orderItem.price(),
                orderItem.quantity()
        );
    }

}
