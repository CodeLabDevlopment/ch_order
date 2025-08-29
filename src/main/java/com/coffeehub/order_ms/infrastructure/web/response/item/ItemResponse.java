package com.coffeehub.order_ms.infrastructure.web.response.item;

import java.math.BigDecimal;

public record ItemResponse(
        String id,
        String productId,
        String productName,
        BigDecimal price,
        Integer quantity
) {

}
