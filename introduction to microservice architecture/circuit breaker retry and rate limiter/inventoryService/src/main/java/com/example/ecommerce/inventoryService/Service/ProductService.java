package com.example.ecommerce.inventoryService.Service;

import com.example.ecommerce.inventoryService.dto.OrderRequestDto;
import com.example.ecommerce.inventoryService.dto.OrderRequestItemDto;
import com.example.ecommerce.inventoryService.dto.ProductDto;
import com.example.ecommerce.inventoryService.entity.Product;
import com.example.ecommerce.inventoryService.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDto> getAllInventory(){
        log.info("fetching all inventory items");
        List<Product> inventories = productRepository.findAll();
        return inventories.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    public ProductDto getProductById(Long id){
        log.info("fetching product with Id:{}", id);
        Optional<Product> inventory = productRepository.findById(id);
        return inventory.map(item -> modelMapper.map(item, ProductDto.class))
                .orElseThrow(()-> new RuntimeException("Inventory not found"));
    }

    @Transactional
    public Double reduceStock(OrderRequestDto orderRequestDto) {

        log.info("Reducing stock for order: {}", orderRequestDto);

        double totalPrice = 0.0;

        for (OrderRequestItemDto item : orderRequestDto.getItems()) {

            Product inventory = productRepository
                    .findById(item.getProductId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Product not found: " + item.getProductId()
                            ));

            if (inventory.getStock() < item.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient stock for product: " + item.getProductId()
                );
            }

            // Reduce stock
            inventory.setStock(
                    (int) (inventory.getStock() - item.getQuantity())
            );

            productRepository.save(inventory);

            // Calculate price
            totalPrice +=
                    inventory.getPrice() * item.getQuantity();

            log.info(
                    "Product {} stock reduced by {}. Remaining stock: {}",
                    item.getProductId(),
                    item.getQuantity(),
                    inventory.getStock()
            );
        }

        log.info("Stock reduction completed. Total price: {}", totalPrice);

        return totalPrice;
    }
}
