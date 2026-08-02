package com.example.springSecurityApp.repository;


import com.example.springSecurityApp.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
