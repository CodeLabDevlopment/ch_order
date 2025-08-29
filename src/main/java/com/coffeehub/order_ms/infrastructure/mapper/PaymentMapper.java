package com.coffeehub.order_ms.infrastructure.mapper;

import com.coffeehub.order_ms.domain.model.Order;
import com.coffeehub.order_ms.domain.dto.PaymentRequest;

import java.util.List;

public final class PaymentMapper {

    public static List<PaymentRequest> toRequest(Order order) {
        return order.items()
                .stream()
                .map(it -> new PaymentRequest(
                        it.productName(),
                        it.productDescription(),
                        it.price(),
                        it.quantity()
                ))
                .toList();
    }

}
