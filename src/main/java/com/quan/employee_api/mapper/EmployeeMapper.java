package com.quan.employee_api.mapper;

import com.quan.employee_api.dto.EmployeeRequestDTO;
import com.quan.employee_api.dto.EmployeeResponseDTO;
import com.quan.employee_api.entity.Employee;

/**
 * Utility class responsible for converting between Employee entities
 * and employee DTOs.
 */
public class EmployeeMapper {
    /**
     * Converts an EmployeeRequestDTO into an Employee entity.
     *
     * @param dto the employee data received from the API request
     * @return a new Employee entity populated with the DTO data
     */
    public static Employee toEntity(EmployeeRequestDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());

        return employee;
    }

    /**
     * Converts an Employee entity into an EmployeeResponseDTO.
     *
     * @param employee the Employee entity to convert
     * @return an EmployeeResponseDTO containing the employee data
     */
    public static EmployeeResponseDTO toResponseDTO(Employee employee) {
        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        employeeResponseDTO.setId(employee.getId());
        employeeResponseDTO.setName(employee.getName());
        employeeResponseDTO.setDepartment(employee.getDepartment());
        employeeResponseDTO.setSalary(employee.getSalary());
        return  employeeResponseDTO;
    }
}
