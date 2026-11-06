package com.quan.employee_api.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quan.employee_api.dto.EmployeeRequestDTO;
import com.quan.employee_api.dto.EmployeeResponseDTO;
import com.quan.employee_api.service.EmployeeService;

/**
 * REST controller that handles HTTP requests related to employees.
 * Delegates business operations to EmployeeService.
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
     * @return a list of EmployeeResponseDTO objects
     */
    @GetMapping("/employees")
    public Page<EmployeeResponseDTO> viewEmployees(@RequestParam(defaultValue = "0") int page, 
                                                    @RequestParam(defaultValue = "5") int size,
                                                    @RequestParam(defaultValue = "id") String sortBy,
                                                    @RequestParam(defaultValue = "ASC") String direction,
                                                    @RequestParam(defaultValue = "") String name,
                                                    @RequestParam(defaultValue = "") String department) {
        return employeeService.viewEmployees(page, size, sortBy, direction, name, department);
    }

    /**
     * Retrieves an employee by ID.
     *
     * @param id the ID of the employee
     * @return the employee data as EmployeeResponseDTO
     */
    @GetMapping("/employees/{id}")
    public EmployeeResponseDTO getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);

    }
    
    /**
     * Creates a new employee.
     *
     * @param employee the employee data provided through EmployeeRequestDTO
     * @return HTTP 201 Created with the created employee data
     */
    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO employee) {
        return ResponseEntity.status(HttpStatus.CREATED)
                     .body(employeeService.createEmployee(employee));
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id the ID of the employee to delete
     * @return HTTP 204 No Content if deletion succeeds
     */
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing employee.
     *
     * @param employee the updated employee data provided through EmployeeRequestDTO
     * @param id the ID of the employee to update
     * @return the updated employee data as EmployeeResponseDTO
     */
    @PutMapping("/employees/{id}")
    public EmployeeResponseDTO updateEmployee(@Valid @RequestBody EmployeeRequestDTO employee, @PathVariable Long id) {
        return employeeService.updateEmployee(employee, id);
    }
}
