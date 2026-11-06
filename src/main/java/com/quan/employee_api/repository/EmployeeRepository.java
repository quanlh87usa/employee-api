package com.quan.employee_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import com.quan.employee_api.entity.Employee;

/**
 * Provides database access operations for Employee entities.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Employee> findByDepartmentIgnoreCase(String department, Pageable pageable);

    Page<Employee> findByNameContainingIgnoreCaseAndDepartmentIgnoreCase(String name, String department, Pageable pageable);
}