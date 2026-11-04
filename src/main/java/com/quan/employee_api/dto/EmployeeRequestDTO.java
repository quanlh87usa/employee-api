package com.quan.employee_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Data Transfer Object used to receive employee data from API requests.
 * Contains validation rules for incoming employee data.
 */
public class EmployeeRequestDTO {
    /** The employee's name. Must not be blank. */
    @NotBlank(message = "Employee name cannot be blank")
    private String name;

    /** The employee's department. */
    private String department;

    /** The employee's salary. Must be zero or greater. */
    @PositiveOrZero(message = "Employee salary cannot be negative")
    private int salary;

    public EmployeeRequestDTO() {

    }

    /**
     * Creates an EmployeeRequestDTO with the provided employee data.
     *
     * @param name the employee's name
     * @param department the employee's department
     * @param salary the employee's salary
     */
    public EmployeeRequestDTO(String name, String department, int salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
