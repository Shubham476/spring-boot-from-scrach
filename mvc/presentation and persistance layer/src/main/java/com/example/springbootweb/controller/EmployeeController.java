package com.example.springbootweb.controller;

import com.example.springbootweb.dto.EmployeeDTO;
import com.example.springbootweb.entity.EmployeeEntity;
import com.example.springbootweb.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path="/getMessage")
    public String getMessage(){
        return "Hi..How are you ?";
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getEmployeeByIdFromJPARepo(@PathVariable(name = "employeeId") Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    @PostMapping(path = "/createEmployeeDetails")
    public EmployeeEntity createNewEmployeeFromJPA(@RequestBody EmployeeEntity inputEmployee){
        return employeeRepository.save(inputEmployee);
    }

    @GetMapping(path = "/getAllEmployee")
    public List<EmployeeEntity> getAllEmployee(@RequestParam(required = false, name = "inputAge") Integer age){
        return employeeRepository.findAll();
    }



/*
    @GetMapping(path = "/employeeDetails/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return new EmployeeDTO(employeeId,"Shubham patil", "shubham@gmail.com", 26, LocalDate.of(2024, 7,25), Boolean.TRUE);
    }
*/

    @GetMapping(path = "/employeeDetails/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long Id){ //here is the change
        return new EmployeeDTO(Id,"Shubham patil", "shubham@gmail.com", 26, LocalDate.of(2024, 7,25), Boolean.TRUE);
    }

    @GetMapping(path = "/getEmployeeMessage")
    public String getEmployeeMessage(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age ){
        return "Hi my name is "+" "+ name + " and I am " + " "+ age + " years old";
    }

/*
    @PostMapping(path = "/createEmployeeDetails")
    public String createNewemployee(){
        return "New Employee Created Sucessfully...!";
    }
*/

//        @PostMapping(path = "/createEmployeeDetails")
//    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//        return inputEmployee;
//    }



}
