package com.coffeehub.order_ms.infrastructure.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderRequest(
        @NotNull(message = "Customer ID is required")
        UUID customerId,
        @NotNull(message = "Product ID is required")
        UUID productId,
        @NotBlank(message = "Product name is required")
        String productName,
        @NotNull(message = "Price is required")
        @Positive(message = "Price must be positive")
        BigDecimal price,
        @NotNull(message = "Quantity is required")
        @Positive(message = "Quantity must be positive")
        Integer quantity
) {

}
