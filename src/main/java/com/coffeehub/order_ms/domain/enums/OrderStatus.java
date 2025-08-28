package com.coffeehub.order_ms.domain.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    RECEIVED("Received"),
    PAID("Paid"),
    PREPARING("Preparing"),
    READY("Ready"),
    OUT_FOR_DELIVERY("Out for Delivery"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }
}
