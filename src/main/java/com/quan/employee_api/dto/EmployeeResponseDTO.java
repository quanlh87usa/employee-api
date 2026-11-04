package com.quan.employee_api.dto;

/**
 * Data Transfer Object used to return employee data to API clients.
 * Defines the employee information exposed by the API.
 */
public class EmployeeResponseDTO {
    /** The unique ID of the employee. */
    private Long id;

    /** The employee's name. */
    private String name;

    /** The employee's department. */
    private String department;

    /** The employee's salary. */
    private int salary;

    public EmployeeResponseDTO() {

    }

    /**
     * Creates an EmployeeResponseDTO with the provided employee data.
     *
     * @param id the employee's unique ID
     * @param name the employee's name
     * @param department the employee's department
     * @param salary the employee's salary
     */
    public EmployeeResponseDTO(Long id, String name, String department, int salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
