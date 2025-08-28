package com.coffeehub.order_ms.infrastructure.gateway;

import com.coffeehub.order_ms.application.gateway.OrderItemGateway;
import com.coffeehub.order_ms.domain.model.Order;
import com.coffeehub.order_ms.domain.model.OrderItem;
import com.coffeehub.order_ms.infrastructure.db.entity.OrderItemEntity;
import com.coffeehub.order_ms.infrastructure.db.repository.OrderItemRepository;
import com.coffeehub.order_ms.infrastructure.mapper.OrderItemMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
public class OrderItemGatewayImpl implements OrderItemGateway {

    private final OrderItemRepository orderItemRepository;

    public OrderItemGatewayImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public void addItemToOrder(Order order, OrderItem item) {
        log.debug("Adding product with name: [{}] to order by id: {}", item.productName(), order.id());
        OrderItemEntity entity = OrderItemMapper.toEntity(order, item);

        orderItemRepository.save(entity);
        log.debug("Item added to order by id: {}", order.id());
    }

    @Override
    public void removeItemFromOrder(Order order, UUID itemId) {
        log.debug("Removing item with id: {} from order by id: {}", itemId, order.id());
        orderItemRepository.deleteById(itemId);
        log.debug("Item with id: {} removed from order by id: {}", itemId, order.id());
    }

    @Override
    public void updateItemQuantity(Order order, UUID itemId, Integer quantity) {
        log.debug("Updating quantity of item with id: {} in order by id: {}", itemId, order.id());

        OrderItem orderItem = findItemById(itemId);
        OrderItemEntity itemEntity = OrderItemMapper.toEntity(orderItem);

        itemEntity.setQuantity(quantity);
        orderItemRepository.save(itemEntity);
        log.debug("Quantity of item with id: {} updated to {} in order by id: {}", itemId, quantity, order.id());
    }

    @Override
    public OrderItem findItemById(UUID itemId) {
        log.debug("Finding item with id: {}", itemId);
        return orderItemRepository.findById(itemId)
                .map(OrderItemMapper::toDomain)
                .orElseThrow(() -> {
                    log.error("Item with id: {} not found", itemId);
                    return new EntityNotFoundException("Item not found");
                });
    }

}
