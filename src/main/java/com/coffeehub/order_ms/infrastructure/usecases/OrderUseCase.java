package com.coffeehub.order_ms.infrastructure.usecases;

import com.coffeehub.order_ms.application.gateway.OrderGateway;
import com.coffeehub.order_ms.application.gateway.StatusHistoryGateway;
import com.coffeehub.order_ms.application.usecases.order.CreateOrderCase;
import com.coffeehub.order_ms.application.usecases.order.GetOrderByIdCase;
import com.coffeehub.order_ms.application.usecases.order.OrderFinalizedCase;
import com.coffeehub.order_ms.application.usecases.order.UpdateOrderStatusCase;
import com.coffeehub.order_ms.infrastructure.gateway.OrderItemGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderUseCase {

    @Bean
    public CreateOrderCase createOrderCase(OrderGateway orderGateway, OrderItemGatewayImpl orderItemGateway) {
        return new CreateOrderCase(orderGateway, orderItemGateway);
    }

    @Bean
    public GetOrderByIdCase getOrderByIdCase(OrderGateway orderGateway) {
        return new GetOrderByIdCase(orderGateway);
    }

    @Bean
    public UpdateOrderStatusCase updateOrderStatusCase(OrderGateway orderGateway, StatusHistoryGateway statusHistoryGateway) {
        return new UpdateOrderStatusCase(orderGateway, statusHistoryGateway);
    }

    @Bean
    public OrderFinalizedCase orderFinalizedCase(OrderGateway orderGateway, StatusHistoryGateway statusHistoryGateway) {
        return new OrderFinalizedCase(orderGateway, statusHistoryGateway);
    }

}
