package com.quan.employee_api;

import java.util.List;
import java.util.Optional;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling employee-related API requests.
 */
@CrossOrigin
@RestController 
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Retrieves all employees.
     *
     * @return a list of all employees
     */
    @GetMapping("/employees")
    public List<Employee> viewEmployees() {
        return employeeRepository.findAll();
    }


    /**
     * Retrieves an employee by ID.
     *
     * @param id the ID of the employee to retrieve
     * @return the employee matching the specified ID
     * @throws EmployeeNotFoundException if the employee is not found
     */
    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeRepository.findById(id)
        .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

    }
    
    /**
     * Creates a new employee.
     *
     * @param employee the employee data to create
     * @return the created employee
     */
    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id the ID of the employee to delete
     * @return a response with HTTP 204 if deletion succeeds, or HTTP 404 if the employee is not found
     */
    @DeleteMapping ("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        employeeRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing employee.
     *
     * @param id the ID of the employee to update
     * @param employee the updated employee data
     * @return the updated employee
     */
    @PutMapping  ("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee, @PathVariable Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Employee existingEmployee = employeeOptional.get();
        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee = employeeRepository.save(existingEmployee);

        return ResponseEntity.ok(existingEmployee);
    }
}
