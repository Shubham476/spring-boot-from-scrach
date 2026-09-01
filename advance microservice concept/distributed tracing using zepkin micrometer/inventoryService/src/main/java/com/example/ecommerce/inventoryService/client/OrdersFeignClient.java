package com.example.ecommerce.inventoryService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "orderService", path = "/api/v1/orders")
public interface OrdersFeignClient {

    @GetMapping()
    String getAllOrders();
}
