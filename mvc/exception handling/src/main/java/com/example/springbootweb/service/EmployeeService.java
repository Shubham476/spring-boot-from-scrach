package com.example.springbootweb.service;

import com.example.springbootweb.dto.EmployeeDTO;
import com.example.springbootweb.entity.EmployeeEntity;
import com.example.springbootweb.exception.ResourceNotFoundException;
import com.example.springbootweb.repository.EmployeeRepository;
import jakarta.persistence.Id;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id){
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1, EmployeeDTO.class));
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity saveEmployeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(saveEmployeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> findAll() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .toList();

    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        boolean exist = employeeRepository.existsById(employeeId);
        if(!exist) throw new ResourceNotFoundException("employee does not exist");
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntry = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntry, EmployeeDTO.class);
    }

    public Boolean deleteEmployeeById(Long id) {
        boolean isExist = employeeRepository.existsById(id);
        if(!isExist) throw new ResourceNotFoundException("employee does not exist");
        if(isExist) employeeRepository.deleteById(id);
        return isExist;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId,
                                             Map<String, Object> updates) {

        Boolean exist = employeeRepository.existsById(employeeId);
        if(!exist) throw new ResourceNotFoundException("employee does not exist");
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();

        updates.forEach((KeyName, KeyValue) -> {
            Field field = ReflectionUtils.getRequiredField(EmployeeEntity.class, KeyName);
            field.setAccessible(true);
            ReflectionUtils.setField(field, employeeEntity, KeyValue);
        });

        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }
}
