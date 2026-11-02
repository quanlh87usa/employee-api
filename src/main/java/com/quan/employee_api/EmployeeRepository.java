package com.quan.employee_api;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides database access operations for Employee entities.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}