package com.coffeehub.order_ms.infrastructure.gateway;

import com.coffeehub.order_ms.application.gateway.StatusHistoryGateway;
import com.coffeehub.order_ms.domain.enums.OrderStatus;
import com.coffeehub.order_ms.infrastructure.db.entity.OrderEntity;
import com.coffeehub.order_ms.infrastructure.db.entity.StatusHistoryEntity;
import com.coffeehub.order_ms.infrastructure.db.repository.OrderRepository;
import com.coffeehub.order_ms.infrastructure.db.repository.StatusHistoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Log4j2
@Service
public class StatusHistoryGatewayImpl implements StatusHistoryGateway {

    private final OrderRepository orderRepository;
    private final StatusHistoryRepository statusHistoryRepository;

    public StatusHistoryGatewayImpl(OrderRepository orderRepository, StatusHistoryRepository statusHistoryRepository) {
        this.orderRepository = orderRepository;
        this.statusHistoryRepository = statusHistoryRepository;
    }

    @Override
    public void addStatusHistory(UUID orderId, OrderStatus status) {
        log.debug("Adding status history for order ID: {} with status: {}", orderId, status);
        OrderEntity order = this.orderRepository.findById(orderId)
                .orElseThrow(() -> {;
                    log.error("Order not found with ID: {}", orderId);
                    return new RuntimeException("Order not found");
                });

        StatusHistoryEntity statusHistory = new StatusHistoryEntity(
                null,
                status,
                order,
                LocalDateTime.now()
        );
        this.statusHistoryRepository.save(statusHistory);
        log.debug("Status history added for order ID: {} with status: {}", orderId, status);
    }

}
