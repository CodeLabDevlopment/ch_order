package com.coffeehub.order_ms.domain.dto;

import java.math.BigDecimal;

public record PaymentRequest(
        String productName,
        String productDescription,
        BigDecimal price,
        Integer quantity
) {

}
