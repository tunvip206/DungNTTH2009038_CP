package com.cp.CP_Project.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    @NotNull(message = "employeeId is required")
    private Integer employeeId;

    @NotBlank(message = "name is required")
    private String name;

    @Email(message = "email should be valid")
    private String email;

    private String location;

    public Employee() {}

    public Employee(Integer employeeId, String name, String email, String location) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.location = location;
    }
}
