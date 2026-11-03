# Employee Management System – Backend

A RESTful backend application for managing employee information, built with Java and Spring Boot.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Spring Validation
- PostgreSQL
- Maven
- REST API

## Features

- Create employee
- Retrieve all employees
- Retrieve employee by ID
- Update employee
- Delete employee
- Input validation
- Centralized exception handling
- HTTP status code handling

## Project Structure

employee-api/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── quan/
│   │   │           └── employee_api/
│   │   │               │
│   │   │               ├── controller/
│   │   │               │   └── EmployeeController.java
│   │   │               │
│   │   │               ├── entity/
│   │   │               │   └── Employee.java
│   │   │               │
│   │   │               ├── exception/
│   │   │               │   ├── ApiErrorResponse.java
│   │   │               │   ├── EmployeeNotFoundException.java
│   │   │               │   └── GlobalExceptionHandler.java
│   │   │               │
│   │   │               ├── repository/
│   │   │               │   └── EmployeeRepository.java
│   │   │               │
│   │   │               ├── service/
│   │   │               │   └── EmployeeService.java
│   │   │               │
│   │   │               └── EmployeeApiApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── quan/
│                   └── employee_api/
│                       └── EmployeeApiApplicationTests.java
│
├── docs/
│   └── testing/
│       └── test-cases.md
│
├── pom.xml
└── README.md

## Requirements

- Java JDK
- Maven
- PostgreSQL

## Installation and Setup

1. Clone the repository:

```bash
git clone YOUR_BACKEND_GITHUB_REPOSITORY_URL
```

2. Navigate to the backend directory:

```bash
cd employee-api
```

3. Configure PostgreSQL database connection in:

```text
src/main/resources/application.properties
```

4. Run the application:

```bash
mvn spring-boot:run
```

The backend API runs on:

```text
http://localhost:8080
```

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | /employees | Get all employees |
| GET | /employees/{id} | Get employee by ID |
| POST | /employees | Create employee |
| PUT | /employee/{id} | Update employee |
| DELETE | /employee/{id} | Delete employee |

## Error Handling

The application handles:

- 400 Bad Request – Invalid input
- 404 Not Found – Employee does not exist
- 500 Internal Server Error – Unexpected server errors

Validation errors are handled centrally using `@RestControllerAdvice`.

## Database

PostgreSQL is used to store employee information.

## Future Improvements

- Add automated backend tests
- Improve API documentation
- Add authentication and authorization
