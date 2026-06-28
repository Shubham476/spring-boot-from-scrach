package com.example.springbootweb.repository;

import com.example.springbootweb.dto.EmployeeDTO;
import com.example.springbootweb.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
