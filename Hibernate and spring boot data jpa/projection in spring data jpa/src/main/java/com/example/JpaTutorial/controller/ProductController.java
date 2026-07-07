package com.example.JpaTutorial.controller;

import com.example.JpaTutorial.entities.ProductEntity;
import com.example.JpaTutorial.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    ProductRepository productRepository;
    private final Integer PAGE_SIZE = 5;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/getAllProductByTitle")
    public List<ProductEntity> getAllProductByTitle(){
        return productRepository.findByTitleOrderByPrice("iPad Air");
    }

    @GetMapping(path = "/getAllProductOrderByPrice")
    public List<ProductEntity> getAllProductOrderByPrice(){
        return productRepository.findByOrderByPrice();
    }

    @GetMapping(path = "/getProductInSortedOrder")
    public List<ProductEntity> getProductInSortedOrder(@RequestParam(defaultValue = "id") String sortByStr){
        return productRepository.findBy(Sort.by(sortByStr));
    }

    @GetMapping(path = "/getAllProductWithPagination")
    public List<ProductEntity> getAllProductWithPagination(@RequestParam(defaultValue = "id") String sortByStr,
                                                           @RequestParam(defaultValue = "0") Integer pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortByStr));
        return productRepository.findAll(pageable).getContent();
    }

    @GetMapping(path = "/findByTitleContainingIgnoreCase")
    public List<ProductEntity> findByTitleContainingIgnoreCase(@RequestParam(defaultValue = "") String title,
                                                               @RequestParam(defaultValue = "id") String sortByStr,
                                                               @RequestParam(defaultValue = "0") Integer pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortByStr));
        return productRepository.findByTitleContainingIgnoreCase(title, pageable);
    }



}
