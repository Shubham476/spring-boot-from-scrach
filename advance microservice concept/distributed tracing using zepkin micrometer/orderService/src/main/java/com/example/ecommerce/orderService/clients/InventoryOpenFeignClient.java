package com.example.ecommerce.orderService.clients;

import com.example.ecommerce.orderService.dto.OrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "INVENTORYSERVICE", path = "/api/v1/inventory")
public interface InventoryOpenFeignClient {

    @PutMapping("/reduce-stock")
    Double reduceStock(@RequestBody OrderRequestDto orderRequestDto);
}