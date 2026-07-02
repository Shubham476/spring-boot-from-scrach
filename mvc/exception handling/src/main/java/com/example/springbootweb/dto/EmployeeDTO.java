package com.example.springbootweb.dto;

import com.example.springbootweb.annotation.EmplyeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Name of the employee cannot be blank")
    @Size(min = 3, max = 10, message = "number of characters in name should be in the range: [3, 10]")
    private String name;

    @NotBlank(message = "email of the employee cannot blank")
    @Email(message = "email should be valid email")
    private String email;

    @NotNull(message = "age of the employee should not be blank")
    @Max(value = 80, message = "Age of the employee cannot greater than 80")
    @Min(value = 18, message = "age of the employee should be greater than 18")
    private Integer age;

    @NotBlank(message = "role of the employee should not be null")
//    @Pattern(regexp = "^(ADMIN|USER$)")
    @EmplyeeRoleValidation
    private String role;

    @NotNull(message = "salary of the employee should not be null")
    @Positive(message = "salary of the employeee should be posotive")
    @Digits(integer = 6, fraction = 2, message = "the salary can be in the form xxxx.YY")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

    @PastOrPresent(message = "DateOfJoining field in employee cannot be in the future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;
}