package com.example.productionReadyFeatures.client.impl;

import com.example.productionReadyFeatures.advice.ApiResponse;
import com.example.productionReadyFeatures.client.EmployeeClient;
import com.example.productionReadyFeatures.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        try {
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    .uri("getAllEmployee")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});

            return employeeDTOList.getData();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        try {
            ApiResponse<EmployeeDTO> employeeDTOList = restClient.get()
                    .uri("{employeeId}", employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            return employeeDTOList.getData();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try {
            ApiResponse<EmployeeDTO> employeeDTOApiResponse = restClient.post()
                    .uri("createEmployeeDetails")
                    .body(employeeDTO)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>(){});

            return employeeDTOApiResponse.getData();
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
