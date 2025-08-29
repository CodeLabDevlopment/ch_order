package com.coffeehub.order_ms.infrastructure.web.routes;

import com.coffeehub.order_ms.domain.enums.OrderStatus;
import com.coffeehub.order_ms.infrastructure.web.request.OrderRequest;
import com.coffeehub.order_ms.infrastructure.web.response.order.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@RequestMapping("/api/v1/orders")
public interface OrderRoutes {

    @PostMapping("/create")
    ResponseEntity<Void> createOrder(@Valid @RequestBody OrderRequest request);

    @GetMapping("{orderId}")
    ResponseEntity<OrderResponse> getOrderById(@PathVariable UUID orderId);

    @PatchMapping("/{orderId}/finalize")
    ResponseEntity<Void> finalizeOrder(@PathVariable UUID orderId);

    @PatchMapping("{orderId}")
    ResponseEntity<Void> updateOrderStatus(@PathVariable UUID orderId, @RequestParam(name = "status") OrderStatus status);

}
