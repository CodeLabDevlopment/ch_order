package com.coffeehub.order_ms.infrastructure.gateway;

import com.coffeehub.order_ms.application.gateway.OrderGateway;
import com.coffeehub.order_ms.domain.enums.OrderStatus;
import com.coffeehub.order_ms.domain.model.Order;
import com.coffeehub.order_ms.infrastructure.db.entity.OrderEntity;
import com.coffeehub.order_ms.infrastructure.db.repository.OrderRepository;
import com.coffeehub.order_ms.infrastructure.mapper.OrderMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
public class OrderGatewayImpl implements OrderGateway {

    private final OrderRepository orderRepository;

    public OrderGatewayImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(UUID customerId, Order order) {
        log.debug("Creating order for customer ID: {}", customerId);

        OrderEntity entity = OrderMapper.toEntity(customerId, order);
        entity = orderRepository.save(entity);

        log.debug("Order created with ID: {}", entity.getId());
        return OrderMapper.toDomain(entity);
    }

    @Override
    public void updateOrderStatus(UUID orderId, OrderStatus status) {
        log.debug("Updating order status for order ID: {} to {}", orderId, status);

        Order order = this.findOrderById(orderId);
        OrderEntity entity = OrderMapper.toEntity(order);

        entity.setStatus(status);
        orderRepository.save(entity);

        log.debug("Order status updated for order ID: {}", orderId);
    }

    @Override
    public Order findOrderById(UUID orderId) {
        log.debug("Finding order by ID: {}", orderId);
        return orderRepository.findById(orderId)
                .map(OrderMapper::toDomain)
                .orElseThrow(() -> {
                    log.error("Order not found with ID: {}", orderId);
                    return new EntityNotFoundException("Order not found");
                });
    }

}
