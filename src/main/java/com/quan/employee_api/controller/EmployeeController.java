package com.quan.employee_api.controller;

import java.util.List;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quan.employee_api.entity.Employee;
import com.quan.employee_api.service.EmployeeService;

/**
 * REST controller responsible for handling employee-related API requests.
 */
@CrossOrigin
@RestController 
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Retrieves all employees.
     *
     * @return a list of all employees
     */
    @GetMapping("/employees")
    public List<Employee> viewEmployees() {
        return employeeService.viewEmployees();
    }


    /**
     * Retrieves an employee by ID.
     *
     * @param id the ID of the employee to retrieve
     * @return the employee matching the specified ID
     */
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);

    }
    
    /**
     * Creates a new employee.
     *
     * @param employee the employee data to create
     * @return the created employee
     */
    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id the ID of the employee to delete
     * @return a response with HTTP 204 if deletion succeeds, or HTTP 404 if the employee is not found
     */
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    /**
     * Updates an existing employee.
     *
     * @param id the ID of the employee to update
     * @param employee the updated employee data
     * @return the updated employee
     */
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee, @PathVariable Long id) {
        return employeeService.updateEmployee(employee, id);
    }
}
