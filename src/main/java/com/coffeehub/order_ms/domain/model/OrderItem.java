package com.coffeehub.order_ms.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItem(
        UUID id,
        Order order,
        UUID productId,
        String productName,
        String productDescription,
        BigDecimal price,
        Integer quantity
) {

}
