# Backend Test Cases

**Project:** Employee Management System  
**Backend:** Java, Spring Boot, Spring Data JPA, PostgreSQL  
**Testing Tool:** Maven, JUnit 5

## 1. Automated Tests

| Test ID | Test Scenario | Expected Result | Status |
|---|---|---|---|
| AT-01 | Verify Spring Application Context loads | Application Context starts successfully | PASS |

**Execution Command:**
```bash
mvn test
```

**Result:** BUILD SUCCESS


## 2. Employee CRUD Tests

| Test ID | Test Scenario | Expected Result | Actual Result | Status |
|---|---|---|---|---|
| CRUD-01 | Retrieve all employees | Return a list of employees | Employees retrieved successfully | PASS |
| CRUD-02 | Retrieve employee by existing ID | Return employee details | Employee details displayed successfully | PASS |
| CRUD-03 | Retrieve employee by non-existing ID | Return HTTP 404 | HTTP 404 returned | PASS |
| CRUD-04 | Create a new employee | Return HTTP 201 Created | Employee created successfully | PASS |
| CRUD-05 | Update an existing employee | Return HTTP 200 OK with updated information | Employee updated successfully | PASS |
| CRUD-06 | Delete an existing employee | Return HTTP 204 No Content | Employee deleted successfully | PASS |
| CRUD-07 | Update or delete a non-existing employee | Return HTTP 404 | HTTP 404 returned | PASS |


## 3. Validation Tests

| Test ID | Test Scenario | Expected Result | Actual Result | Status |
|---|---|---|---|---|
| VAL-01 | Create employee with blank name | Return HTTP 400 Bad Request | Validation error returned | PASS |
| VAL-02 | Create employee with negative salary | Return HTTP 400 Bad Request | Validation error returned | PASS |
| VAL-03 | Update employee with invalid data | Return HTTP 400 Bad Request | Validation error returned | PASS |

## 4. Error Handling Tests

| Test ID | Test Scenario | Expected Result | Actual Result | Status |
|---|---|---|---|---|
| ERR-01 | Retrieve employee with non-existing ID | Return HTTP 404 Not Found | HTTP 404 returned | PASS |
| ERR-02 | Update employee with non-existing ID | Return HTTP 404 Not Found | HTTP 404 returned | PASS |
| ERR-03 | Delete employee with non-existing ID | Return HTTP 404 Not Found | HTTP 404 returned | PASS |
| ERR-04 | Connect frontend while backend is stopped | Display connection error message | Connection error displayed | PASS |

## 5. Frontend Integration Tests

| Test ID | Test Scenario | Expected Result | Actual Result | Status |
|---|---|---|---|---|
| FE-01 | Load employee list from backend | Display employee list | Employee list displayed | PASS |
| FE-02 | Add employee through frontend | Save employee and refresh list | Employee added successfully | PASS |
| FE-03 | Edit employee through frontend | Update employee information and refresh list | Employee updated successfully | PASS |
| FE-04 | Delete employee through frontend | Delete employee and refresh list | Employee deleted successfully | PASS |
| FE-05 | View employee details | Display employee information | Employee details displayed | PASS |
| FE-06 | Display validation errors | Show validation messages to user | Validation messages displayed | PASS |