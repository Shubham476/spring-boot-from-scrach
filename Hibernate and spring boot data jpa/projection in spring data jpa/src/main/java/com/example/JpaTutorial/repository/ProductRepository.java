package com.example.JpaTutorial.repository;

import com.example.JpaTutorial.Interface.ProductInfo;
import com.example.JpaTutorial.dto.ProductDTOInfo;
import com.example.JpaTutorial.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByTitle(String title);

    List<ProductEntity> findByCreatedAtAfter(LocalDateTime localDateTime);

    List<ProductEntity> findByQuantityAndPriceGreaterThan(int i, BigDecimal bigDecimal);

    List<ProductEntity> findByTitleLike(String s);

    List<ProductEntity> findByTitleContainingIgnoreCase(String s);

    @Query("select e from ProductEntity e where e.title=?1 and e.price=?2")
    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);

    List<ProductEntity> findByTitleOrderByPrice(String s);

    List<ProductEntity> findByOrderByPrice();

    List<ProductEntity> findBy(Sort sort);

    List<ProductEntity> findByTitleContainingIgnoreCase(String s, Pageable pageable);

    @Query("select p.title as title, p.price as price, p.quantity as quantity from ProductEntity p")
    List<ProductInfo> findAllBy();

    @Query("SELECT new com.example.JpaTutorial.dto.ProductDTOInfo(p.title,p.price) FROM ProductEntity p")
    List<ProductDTOInfo> getProducts();

    <T> List<T> findByTitle(String title, Class<T> type);
}
