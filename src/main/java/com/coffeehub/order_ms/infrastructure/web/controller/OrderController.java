package com.coffeehub.order_ms.infrastructure.web.controller;

import com.coffeehub.order_ms.application.usecases.order.OrderFinalizedCase;
import com.coffeehub.order_ms.application.usecases.order.CreateOrderCase;
import com.coffeehub.order_ms.application.usecases.order.GetOrderByIdCase;
import com.coffeehub.order_ms.application.usecases.order.UpdateOrderStatusCase;
import com.coffeehub.order_ms.domain.enums.OrderStatus;
import com.coffeehub.order_ms.domain.model.Order;
import com.coffeehub.order_ms.infrastructure.client.payment.service.PaymentService;
import com.coffeehub.order_ms.infrastructure.mapper.OrderMapper;
import com.coffeehub.order_ms.infrastructure.mapper.PaymentMapper;
import com.coffeehub.order_ms.infrastructure.web.request.OrderRequest;
import com.coffeehub.order_ms.domain.dto.PaymentRequest;
import com.coffeehub.order_ms.domain.dto.PaymentResponse;
import com.coffeehub.order_ms.infrastructure.web.response.order.OrderResponse;
import com.coffeehub.order_ms.infrastructure.web.routes.OrderRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderRoutes {

    private final PaymentService paymentService;
    private final CreateOrderCase createOrderCase;
    private final GetOrderByIdCase getOrderByIdCase;
    private final UpdateOrderStatusCase updateOrderStatusCase;
    private final OrderFinalizedCase orderFinalizedCase;

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
    public ResponseEntity<Void> finalizeOrder(UUID orderId) {
        Order order = this.getOrderByIdCase.execute(orderId);

        List<PaymentRequest> paymentRequests = PaymentMapper.toRequest(order);
        PaymentResponse response = this.paymentService.createCheckout(paymentRequests);

        this.orderFinalizedCase.execute(orderId, response);

        URI paymentUri = URI.create(response.checkoutUrl());
        return ResponseEntity.created(paymentUri).build();
    }

    @Override
    public ResponseEntity<Void> updateOrderStatus(UUID orderId, OrderStatus status) {
        this.updateOrderStatusCase.execute(orderId, status);
        return ResponseEntity.noContent().build();
    }

}
