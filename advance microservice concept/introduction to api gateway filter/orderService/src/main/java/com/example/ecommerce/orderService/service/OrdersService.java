package com.example.ecommerce.orderService.service;

import com.example.ecommerce.orderService.clients.InventoryOpenFeignClient;
import com.example.ecommerce.orderService.dto.OrderRequestDto;
import com.example.ecommerce.orderService.entity.OrderItem;
import com.example.ecommerce.orderService.entity.OrderStatus;
import com.example.ecommerce.orderService.entity.Orders;
import com.example.ecommerce.orderService.repository.OrdersRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersService {

    private final OrdersRepository ordersRepository ;
    private final ModelMapper modelMapper;
    private final InventoryOpenFeignClient inventoryOpenFeignClient;

    public List<OrderRequestDto> getAllOrders() {
        log.info("Fetching all orders");
            List<Orders> orders = ordersRepository.findAll();
        return orders.stream().map(order -> modelMapper.map(order, OrderRequestDto.class)).toList();
    }

    public OrderRequestDto getOrderById(Long id){
        log.info("fetching order with id");
        Orders orders = ordersRepository.findById(id).orElseThrow(()->new RuntimeException("Order not found"));
        return modelMapper.map(orders, OrderRequestDto.class);
    }

//    @Retry(name = "inventoryRetry", fallbackMethod = "createOrderFallback")
//    @RateLimiter(name = "inventoryRateLimiter", fallbackMethod = "createOrderFallback")
    @CircuitBreaker(name = "inventoryCircuitBreaker", fallbackMethod = "createOrderFallback")
    public OrderRequestDto createOrder(OrderRequestDto orderRequestDto) {
        log.info("Calling creteOrder method");
        Double totalPrice = inventoryOpenFeignClient.reduceStock(orderRequestDto);
        Orders orders = modelMapper.map(orderRequestDto, Orders.class);
        for (OrderItem orderItem: orders.getItems()){
            orderItem.setOrders(orders);
        }
        orders.setTotalPrice(totalPrice);
        orders.setOrderStatus(OrderStatus.CONFIRMED);
        Orders savedOrder = ordersRepository.save(orders);
        return modelMapper.map(savedOrder, OrderRequestDto.class);
    }

    public OrderRequestDto createOrderFallback(OrderRequestDto orderRequestDto, Throwable throwable){
        log.error("fallbackError due to: {}", throwable.getMessage());
        return new OrderRequestDto();
    }
}
