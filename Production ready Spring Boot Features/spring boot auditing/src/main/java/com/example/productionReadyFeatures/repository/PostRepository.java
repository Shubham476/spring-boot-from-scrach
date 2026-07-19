package com.example.productionReadyFeatures.repository;

import com.example.productionReadyFeatures.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
