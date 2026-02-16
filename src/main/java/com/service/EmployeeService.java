package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Employee;
import com.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // --- Save or update employee ---
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // --- Get all employees ---
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // --- Get employee by ID (Optional) ---
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // --- Delete employee by ID ---
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    // --- Send email simulation (used for debug/demo) ---
    public void sendRegistrationSuccessEmail(String email, String employeeName) {
        // Simulated email sending logic
        System.out.println("Email sent to: " + email + " for employee " + employeeName);
    }
}
