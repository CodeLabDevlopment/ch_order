package com.coffeehub.order_ms.infrastructure.web.response.statushistory;

public record StatusHistoryResponse(
        String id,
        String status,
        String changedAt
) {

}
