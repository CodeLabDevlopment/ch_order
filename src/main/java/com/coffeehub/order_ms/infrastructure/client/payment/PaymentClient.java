package com.coffeehub.order_ms.infrastructure.client.payment;

import com.coffeehub.order_ms.domain.dto.PaymentRequest;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "payment-ms", url = "${feign.client.payment-ms.url}")
public interface PaymentClient {

    @PostMapping("/checkout")
    Response createCheckout(List<PaymentRequest> request);

}
