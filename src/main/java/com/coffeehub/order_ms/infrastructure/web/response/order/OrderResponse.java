package com.coffeehub.order_ms.infrastructure.web.response.order;

import com.coffeehub.order_ms.infrastructure.web.response.item.ItemResponse;
import com.coffeehub.order_ms.infrastructure.web.response.statushistory.StatusHistoryResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(
        String id,
        String deliveryId,
        BigDecimal totalAmount,
        String status,
        List<ItemResponse> items,
        List<StatusHistoryResponse> statusHistory,
        String createdAt,
        String updatedAt
) {

}
