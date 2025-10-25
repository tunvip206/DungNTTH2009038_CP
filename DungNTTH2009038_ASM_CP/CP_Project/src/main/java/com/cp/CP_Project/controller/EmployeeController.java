package com.cp.CP_Project.controller;

import com.cp.CP_Project.model.Employee;
import com.cp.CP_Project.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public List<Employee> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Integer id) {
        Employee emp = service.findById(id);
        if (emp == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(emp);
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody Employee emp) {
        if (service.findById(emp.getEmployeeId()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee with id already exists");
        }
        service.save(emp);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Employee updated) {
        Employee updatedEmp = service.update(id, updated);
        if (updatedEmp == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        return ResponseEntity.ok(updatedEmp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        Employee removed = service.delete(id);
        if (removed == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        return ResponseEntity.ok("Employee deleted");
    }
}