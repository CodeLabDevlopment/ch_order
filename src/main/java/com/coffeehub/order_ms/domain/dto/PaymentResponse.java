package com.coffeehub.order_ms.domain.dto;

import java.util.UUID;

public record PaymentResponse(
        UUID paymentId,
        String checkoutUrl
) {

}
