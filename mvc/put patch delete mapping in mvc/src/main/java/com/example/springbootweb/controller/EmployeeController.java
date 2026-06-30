package com.example.springbootweb.controller;

import com.example.springbootweb.dto.EmployeeDTO;
import com.example.springbootweb.repository.EmployeeRepository;
import com.example.springbootweb.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeRepository employeeRepository, EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path="/getMessage")
    public String getMessage(){
        return "Hi..How are you ?";
    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeByIdFromJPARepo(@PathVariable(name = "employeeId") Long id){
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/createEmployeeDetails")
    public ResponseEntity<EmployeeDTO> createNewEmployeeFromJPA(@RequestBody EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping(path = "/getAllEmployee")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(@RequestParam(required = false, name = "inputAge") Integer age){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PutMapping(path = "/updateEmployeeDetails/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable(name = "employeeId") Long Id, @RequestBody EmployeeDTO employeeDTO){ //here is the change
        return ResponseEntity.ok(employeeService.updateEmployeeById(Id, employeeDTO));
    }

    @DeleteMapping(path = "/deleteEmployeeDetails/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable(name = "employeeId") Long Id){ //here is the change
        Boolean gotDeleted = employeeService.deleteEmployeeById(Id);
        if (gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/updatePartialDetails/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates, @PathVariable(name = "employeeId") Long Id){
        EmployeeDTO employeeDTO =  employeeService.updatePartialEmployeeById(Id, updates);
        if(employeeDTO== null) ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }



}
