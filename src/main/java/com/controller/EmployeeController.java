package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import com.entity.Employee;
import com.entity.Role;
import com.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JavaMailSender mailSender;

    // --- Get all employees ---
    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // --- Register new employee with email notification ---
    @PostMapping("/register")
    public ResponseEntity<Employee> registerEmployee(@RequestBody Employee employee) {
        // Set default role
        employee.setRole(Role.ADMIN);
        // employee.setRole(Role.EMPLOYEE);

        // Save to database
        Employee savedEmployee = employeeService.saveEmployee(employee);

        // Send registration success email
        sendRegistrationSuccessEmail(savedEmployee.getEmail(), savedEmployee.getName());

        return ResponseEntity.ok(savedEmployee);
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> employeeOpt = employeeService.getEmployeeById(id);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            employee.setName(updatedEmployee.getName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setMobile(updatedEmployee.getMobile());
            employee.setPassword(updatedEmployee.getPassword());// Add encoding if needed
            employee.setDist(updatedEmployee.getDist());
            employee.setCity(updatedEmployee.getCity());
            employee.setAddress(updatedEmployee.getAddress());
            employee.setRole(updatedEmployee.getRole());

            Employee savedEmployee = employeeService.saveEmployee(employee);
            return ResponseEntity.ok(savedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Employee loginEmployee) {
        List<Employee> employees = employeeService.getAllEmployees();

        for (Employee employee : employees) {
            if (employee.getEmail().equalsIgnoreCase(loginEmployee.getEmail()) &&
                    employee.getPassword().equals(loginEmployee.getPassword())) {
                return ResponseEntity.ok("Login successful. Role: " + employee.getRole());
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    // --- Delete employee by ID ---
    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
        Optional<Employee> employeeOpt = employeeService.getEmployeeById(id);
        if (employeeOpt.isPresent()) {
            employeeService.deleteEmployeeById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // --- Internal method to send welcome email ---
    private void sendRegistrationSuccessEmail(String to, String employeeName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Welcome to Employee Management System!");
        message.setText("Hello " + employeeName
                + ",\n\nYour registration as an employee was successful.\n\nThank you,\n HR Team");
        mailSender.send(message);
    }
}
