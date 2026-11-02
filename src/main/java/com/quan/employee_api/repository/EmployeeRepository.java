package com.quan.employee_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quan.employee_api.entity.Employee;

/**
 * Provides database access operations for Employee entities.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}