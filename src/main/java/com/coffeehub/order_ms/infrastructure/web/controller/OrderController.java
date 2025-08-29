package com.coffeehub.order_ms.infrastructure.web.controller;

import com.coffeehub.order_ms.application.usecases.order.CreateOrderCase;
import com.coffeehub.order_ms.application.usecases.order.GetOrderByIdCase;
import com.coffeehub.order_ms.application.usecases.order.UpdateOrderStatusCase;
import com.coffeehub.order_ms.domain.enums.OrderStatus;
import com.coffeehub.order_ms.domain.model.Order;
import com.coffeehub.order_ms.infrastructure.mapper.OrderMapper;
import com.coffeehub.order_ms.infrastructure.web.request.OrderRequest;
import com.coffeehub.order_ms.infrastructure.web.response.order.OrderResponse;
import com.coffeehub.order_ms.infrastructure.web.routes.OrderRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderRoutes {

    private final CreateOrderCase createOrderCase;
    private final GetOrderByIdCase getOrderByIdCase;
    private final UpdateOrderStatusCase updateOrderStatusCase;

    @Override
    public ResponseEntity<Void> createOrder(OrderRequest request) {
        this.createOrderCase.execute(
                request.customerId(),
                request.productId(),
                request.productName(),
                request.productDescription(),
                request.price(),
                request.quantity()
        );
        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity<OrderResponse> getOrderById(UUID orderId) {
        Order order = this.getOrderByIdCase.execute(orderId);
        OrderResponse response = OrderMapper.toResponse(order);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Void> updateOrderStatus(UUID orderId, OrderStatus status) {
        this.updateOrderStatusCase.execute(orderId, status);
        return ResponseEntity.noContent().build();
    }

}
