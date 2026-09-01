package com.example.ecommerce.inventoryService.dto;

import lombok.Data;

@Data
public class OrderRequestItemDto {
    private Long productId;
    private Long quantity;
}
