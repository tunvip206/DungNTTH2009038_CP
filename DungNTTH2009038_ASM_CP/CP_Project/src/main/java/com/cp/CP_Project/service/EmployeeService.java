package com.cp.CP_Project.service;

import com.cp.CP_Project.model.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    @PostConstruct
    // helper for demo to pre-populate
    public void initSample() {
        save(new Employee(1, "Alice", "alice@example.com", "Bangkok"));
        save(new Employee(2, "Bob", "bob@example.com", "Phuket"));
    }

    private final Map<Integer, Employee> employeeMap = Collections.synchronizedMap(new HashMap<>());

    public List<Employee> findAll() {
        synchronized (employeeMap) {
            return new ArrayList<>(employeeMap.values());
        }
    }

    public Employee findById(Integer id) {
        return employeeMap.get(id);
    }
    public Employee save(Employee emp) {
        // employeeId must be unique; will overwrite if exists
        employeeMap.put(emp.getEmployeeId(), emp);
        return emp;
    }

    public Employee update(Integer id, Employee updated) {
        synchronized (employeeMap) {
            Employee existing = employeeMap.get(id);
            if (existing == null) return null;
            // Do not allow updating employeeId
            if (updated.getName() != null) existing.setName(updated.getName());
            if (updated.getEmail() != null) existing.setEmail(updated.getEmail());
            if (updated.getLocation() != null) existing.setLocation(updated.getLocation());
            employeeMap.put(id, existing);
            return existing;
        }
    }

    public Employee delete(Integer id) {
        return employeeMap.remove(id);
    }

}
