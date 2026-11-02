package com.quan.employee_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.quan.employee_api.entity.Employee;
import com.quan.employee_api.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    /**
     * Retrieves all employees.
     *
     * @return a list of all employees
     */
    public List<Employee> viewEmployees() {
        return employeeRepository.findAll();
    }


    /**
     * Retrieves an employee by ID.
     *
     * @param id the ID of the employee to retrieve
     * @return the employee matching the specified ID
     */
    public ResponseEntity<Employee> getEmployee(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employeeOptional.get());

    }
    
    /**
     * Creates a new employee.
     *
     * @param employee the employee data to create
     * @return the created employee
     */
    public ResponseEntity<Employee> createEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id the ID of the employee to delete
     * @return a response with HTTP 204 if deletion succeeds, or HTTP 404 if the employee is not found
     */
    public ResponseEntity<Void> deleteEmployee(Long id) {
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
    public ResponseEntity<Employee> updateEmployee(Employee employee, Long id) {
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
