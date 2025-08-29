package com.coffeehub.order_ms.domain.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    RECEIVED("Received"),
    PAID("Paid"), // TODO: Após efetuar pagamento o serviço de pagamento irá enviar uma mensagem para esse serviço notificando
    PROCESSING("Processing"), // TODO: Após pagamento pago
    SENT("Sent"), // TODO: Após serviço de delivery enviar para entrega
    DELIVERED("Delivered"), // TODO: Após serviço do correio notificar que foi entregue
    CANCELLED("Cancelled"); // TODO: Quando pedido é cancelado

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }
}
