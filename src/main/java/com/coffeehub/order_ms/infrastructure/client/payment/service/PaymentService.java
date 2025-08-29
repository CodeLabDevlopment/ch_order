package com.coffeehub.order_ms.infrastructure.client.payment.service;

import com.coffeehub.order_ms.infrastructure.client.payment.PaymentClient;
import com.coffeehub.order_ms.domain.dto.PaymentRequest;
import com.coffeehub.order_ms.domain.dto.PaymentResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Log4j2
@Service
public class PaymentService {

    private final PaymentClient paymentClient;
    private final ObjectMapper objectMapper;

    public PaymentService(PaymentClient paymentClient, ObjectMapper objectMapper) {
        this.paymentClient = paymentClient;
        this.objectMapper = objectMapper;
    }

    public PaymentResponse createCheckout(List<PaymentRequest> request) {
        log.info("Creating checkout with items: {}", request);
        Response response = this.paymentClient.createCheckout(request);

        try (InputStream in = response.body().asInputStream()) {
            PaymentResponse paymentResponse = objectMapper.readValue(in, PaymentResponse.class);
            log.info("Checkout created successfully: {}", paymentResponse);
            return paymentResponse;
        } catch (Exception e) {
            log.error("Error creating checkout", e);
            throw new RuntimeException("Failed to create checkout", e);
        }
    }

}
