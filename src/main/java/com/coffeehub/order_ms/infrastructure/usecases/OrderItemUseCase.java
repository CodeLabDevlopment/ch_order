package com.coffeehub.order_ms.infrastructure.usecases;

import com.coffeehub.order_ms.application.gateway.OrderGateway;
import com.coffeehub.order_ms.application.gateway.OrderItemGateway;
import com.coffeehub.order_ms.application.usecases.order.item.GetItemByIdCase;
import com.coffeehub.order_ms.application.usecases.order.item.RemoveItemCase;
import com.coffeehub.order_ms.application.usecases.order.item.UpdateQuantityItemCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderItemUseCase {

    @Bean
    public GetItemByIdCase getItemByIdCase(OrderItemGateway orderItemGateway) {
        return new GetItemByIdCase(orderItemGateway);
    }

    @Bean
    public RemoveItemCase removeItemCase(OrderGateway orderGateway, OrderItemGateway orderItemGateway) {
        return new RemoveItemCase(orderGateway, orderItemGateway);
    }

    @Bean
    public UpdateQuantityItemCase updateQuantityItemCase(OrderGateway orderGateway, OrderItemGateway orderItemGateway) {
        return new UpdateQuantityItemCase(orderGateway, orderItemGateway);
    }

}
