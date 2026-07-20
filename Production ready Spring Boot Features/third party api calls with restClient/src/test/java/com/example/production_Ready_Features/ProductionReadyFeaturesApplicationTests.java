package com.example.production_Ready_Features;

import com.example.productionReadyFeatures.ProductionReadyFeaturesApplication;
import com.example.productionReadyFeatures.client.EmployeeClient;
import com.example.productionReadyFeatures.dto.EmployeeDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest(classes = ProductionReadyFeaturesApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductionReadyFeaturesApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	@Order(3)
	void getAllEmployee() {
		List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployee();
		System.out.println(employeeDTOList);
	}

	@Test
	@Order(2)
	void getEmployeeById() {
		EmployeeDTO employeeDTOList = employeeClient.getEmployeeById(1L);
		System.out.println(employeeDTOList);
	}

	@Test
	@Order(1)
	void createNewEmployee(){
		EmployeeDTO employeeDTO = new EmployeeDTO(null, "shubham", "shubham@gmail.com", 26, "USER", 70000.0,
				LocalDate.of(2024, 7, 25), true);
		EmployeeDTO savedEmployeeDTO = employeeClient.createNewEmployee(employeeDTO);
		System.out.println(savedEmployeeDTO);
	}
}
