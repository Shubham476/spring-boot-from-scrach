package com.example.ecommerce.inventoryService.controller;

import com.example.ecommerce.inventoryService.Service.ProductService;
import com.example.ecommerce.inventoryService.client.OrdersFeignClient;
import com.example.ecommerce.inventoryService.dto.OrderRequestDto;
import com.example.ecommerce.inventoryService.dto.ProductDto;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
//@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final DiscoveryClient discoveryClient;
//    private final RestClient restClient;
    private final OrdersFeignClient ordersFeignClient;


    @GetMapping("/fetchOrders")
    public String fetchFromOrdersService(){
//        ServiceInstance orderService = discoveryClient.getInstances("orderService").getFirst();
//        log.info("Service Instance {}", orderService);
//        log.info("Order Service Instance uri {}", orderService.getUri());
//        return restClient.get()
//                .uri(orderService.getUri()+"/api/v1/orders")
//                .retrieve()
//                .body(String.class);

        return ordersFeignClient.getAllOrders();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllInventory(){
        List<ProductDto> inventories = productService.getAllInventory();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getInventoryById(@PathVariable Long id){
        ProductDto inventory = productService.getProductById(id);
        return ResponseEntity.ok(inventory);
    }

    @PutMapping("/reduce-stock")
    public ResponseEntity<Double> reduceStock(@RequestBody OrderRequestDto orderRequestDto){
        Double totalPrice = productService.reduceStock(orderRequestDto);
        return ResponseEntity.ok(totalPrice);
    }


}
