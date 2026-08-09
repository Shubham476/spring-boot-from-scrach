package com.example.springSecurityApp.dto;

import com.example.springSecurityApp.entities.enums.Permission;
import com.example.springSecurityApp.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDTO {

    private String email;
    private String password;
    private String name;
    private Set<Role> roles;
    private Set<Permission> permissions;
}
