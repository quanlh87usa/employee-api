package com.quan.employee_api.service;

import com.quan.employee_api.dto.EmployeeRequestDTO;
import com.quan.employee_api.dto.EmployeeResponseDTO;
import com.quan.employee_api.entity.Employee;
import com.quan.employee_api.exception.EmployeeNotFoundException;
import com.quan.employee_api.mapper.EmployeeMapper;
import com.quan.employee_api.repository.EmployeeRepository;

import java.util.Locale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<EmployeeResponseDTO> viewEmployees(int page, int size, 
                                                    String sortBy, String direction,
                                                    String name, String department) {
        if (page < 0) {
            throw new IllegalArgumentException("Page index cannot be negative.");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("Page size must be greater than zero.");
        }

        // check validate for sortBy requestParam
        if(!sortBy.equalsIgnoreCase("id") &&
            !sortBy.equalsIgnoreCase("name") &&
            !sortBy.equalsIgnoreCase("department") &&
            !sortBy.equalsIgnoreCase("salary")) {
            throw new IllegalArgumentException("sortBy is invalid");
        }
        Sort sort = Sort.by(sortBy);
        
        // check validate for direction requestParam
        direction = direction.toUpperCase(Locale.ROOT);
        if(direction.equals("DESC")) {
            sort = sort.descending();
        }
        if(!direction.equals("DESC") && !direction.equals("ASC")) {
            throw new IllegalArgumentException("direction is invalid");
        }

        // config pageable
        Pageable pageable = PageRequest.of(page, size, sort);


        // excute search action
        Page<Employee> employees = null;
        if(name.isBlank() && department.isBlank()) {
            employees = employeeRepository.findAll(pageable);
        } else if(!name.isBlank() && department.isBlank()) {
            employees = employeeRepository.findByNameContainingIgnoreCase(name, pageable);
        } else if(!department.isBlank() && name.isBlank()) {
            employees = employeeRepository.findByDepartmentIgnoreCase(department, pageable);
        } else {
            employees = employeeRepository.findByNameContainingIgnoreCaseAndDepartmentIgnoreCase(name, department, pageable);
        }
        
        return employees.map(EmployeeMapper::toResponseDTO);
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
