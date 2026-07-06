package com.example.JpaTutorial;

import com.example.JpaTutorial.entities.ProductEntity;
import com.example.JpaTutorial.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaTutorialApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository(){
		ProductEntity productEntity = ProductEntity.builder()
				.sku("SKU011")
				.title("Mouse")
				.price(BigDecimal.valueOf(2599.00))
				.quantity(12)
				.build();

		ProductEntity savedProductEntity = productRepository.save(productEntity);
		System.out.println("savedProductEntity"+ savedProductEntity);
	}

	@Test
	void getRepository(){
		List<ProductEntity> entities = productRepository.findAll();
		System.out.println(entities);
	}

	@Test
	void getRepoById(){
		List<ProductEntity> findByTitle = productRepository.findByTitle("Mouse");
		System.out.println("findByTitle"+findByTitle);
	}

	@Test
	void createAtAfter(){
		List<ProductEntity> createdAtAfter = productRepository.findByCreatedAtAfter(
				LocalDateTime.of(2026,1,1,0,0,0 )
		);
		System.out.println("createdAtAfter" + createdAtAfter);
	}

	@Test
	void findByQuantityAndPrice(){
		List<ProductEntity> findByQuantityAndPrice = productRepository.
				findByQuantityAndPriceGreaterThan(10, BigDecimal.valueOf(2599.00));
		System.out.println("findByQuantityAndPrice" + findByQuantityAndPrice);
	}

	@Test
	void findByTitleLike(){
		List<ProductEntity> findByTitleLike = productRepository.findByTitleLike("%apple%");
		System.out.println("findByTitleLike" + findByTitleLike);
	}

	@Test
	void findByTitleContainingIgnoreCase(){
		List<ProductEntity> findByTitleContainingIgnoreCase = productRepository.findByTitleContainingIgnoreCase("APPLE");
		System.out.println("findByTitleContainingIgnoreCase" + findByTitleContainingIgnoreCase);
	}

	@Test //custom query
	void findByTitleAndPrice(){
		Optional<ProductEntity> findByTitleAndPrice = productRepository.
				findByTitleAndPrice("Apple", BigDecimal.valueOf(25000.00));
		System.out.println("findByTitleAndPrice" + findByTitleAndPrice);
	}



}
