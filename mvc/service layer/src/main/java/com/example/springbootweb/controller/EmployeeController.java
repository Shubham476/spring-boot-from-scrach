package com.example.springbootweb.controller;

import com.example.springbootweb.dto.EmployeeDTO;
import com.example.springbootweb.entity.EmployeeEntity;
import com.example.springbootweb.repository.EmployeeRepository;
import com.example.springbootweb.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    public EmployeeDTO getEmployeeByIdFromJPARepo(@PathVariable(name = "employeeId") Long id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping(path = "/createEmployeeDetails")
    public EmployeeDTO createNewEmployeeFromJPA(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.save(inputEmployee);
    }

    @GetMapping(path = "/getAllEmployee")
    public List<EmployeeDTO> getAllEmployee(@RequestParam(required = false, name = "inputAge") Integer age){
        return employeeService.findAll();
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
