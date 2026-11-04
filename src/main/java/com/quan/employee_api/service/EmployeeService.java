package com.quan.employee_api.service;

import java.util.List;
import java.util.stream.Collectors;

import com.quan.employee_api.dto.EmployeeRequestDTO;
import com.quan.employee_api.dto.EmployeeResponseDTO;
import com.quan.employee_api.entity.Employee;
import com.quan.employee_api.exception.EmployeeNotFoundException;
import com.quan.employee_api.mapper.EmployeeMapper;
import com.quan.employee_api.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

/**
 * Service layer responsible for employee business logic.
 * Handles employee operations and coordinates data conversion
 * between DTOs and entities.
 */
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    /**
     * Retrieves all employees.
     *
     * @return a list of EmployeeResponseDTO objects
     */
    public List<EmployeeResponseDTO> viewEmployees() {
        return employeeRepository.findAll().stream().map(EmployeeMapper::toResponseDTO).collect(Collectors.toList());
    }

    /**
     * Retrieves an employee by ID.
     *
     * @param id the ID of the employee to retrieve
     * @return the employee data as EmployeeResponseDTO
     * @throws EmployeeNotFoundException if the employee does not exist
     */
    public EmployeeResponseDTO getEmployee(Long id) {
        return EmployeeMapper.toResponseDTO(employeeRepository.findById(id)
                    .orElseThrow(() -> 
                        new EmployeeNotFoundException("Not found employee")));
    }
    
    /**
     * Creates a new employee.
     *
     * @param employeeRequestDTO the employee data used to create a new employee
     * @return the created employee data as EmployeeResponseDTO
     */
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = EmployeeMapper.toEntity(employeeRequestDTO);
        return EmployeeMapper.toResponseDTO(employeeRepository.save(employee));
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id the ID of the employee to delete
     * @throws EmployeeNotFoundException if the employee does not exist
     */
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee"));
        employeeRepository.delete(employee);
    }

    /**
     * Updates an existing employee.
     *
     * @param employee the updated employee data provided through EmployeeRequestDTO
     * @param id the ID of the employee to update
     * @return the updated employee data as EmployeeResponseDTO
     * @throws EmployeeNotFoundException if the employee does not exist
     */
    public EmployeeResponseDTO updateEmployee(EmployeeRequestDTO employee, Long id) {

        Employee existingEmployee = employeeRepository.findById(id)
                                                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee"));
        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee = employeeRepository.save(existingEmployee);

        return EmployeeMapper.toResponseDTO(existingEmployee);
    }
}
