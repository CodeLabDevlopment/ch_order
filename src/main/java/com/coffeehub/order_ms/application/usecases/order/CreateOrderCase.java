package com.coffeehub.order_ms.application.usecases.order;

import com.coffeehub.order_ms.application.gateway.OrderGateway;
import com.coffeehub.order_ms.application.gateway.OrderItemGateway;
import com.coffeehub.order_ms.domain.enums.OrderStatus;
import com.coffeehub.order_ms.domain.model.Order;
import com.coffeehub.order_ms.domain.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateOrderCase(OrderGateway orderGateway, OrderItemGateway orderItemGateway) {

    public void execute(UUID customerId, UUID productId, String productName, String productDescription,BigDecimal price,
                        Integer quantity) {
        Order order = new Order(
                null,
                customerId,
                null,
                null,
                price.multiply(BigDecimal.valueOf(quantity)),
                OrderStatus.RECEIVED,
                List.of(),
                List.of(),
                null,
                null
        );
        OrderItem item = new OrderItem(
                null,
                order,
                productId,
                productName,
                productDescription,
                price,
                quantity
        );

        Order orderSaved = orderGateway.createOrder(customerId, order);
        orderItemGateway.addItemToOrder(orderSaved, item);
    }

}
