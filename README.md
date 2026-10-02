# ERP-ASSESSMENT-student-management-backend

A Student Management REST API built with **Spring Boot**, **Spring Data JPA** and **MySQL**.

## Tech Stack
- Java 21
- Spring Boot 4 (Spring Web MVC, Spring Data JPA, Validation)
- MySQL 8
- Maven

## Features
- CRUD operations for students
- Search by name and register number
- Filter by department, year and semester (combinable)
- Field validation with clear error messages
- Duplicate register number check (409)
- Global exception handling (400, 404, 409)

## Setup
1. Install Java 21, Maven and MySQL.
2. Create the database by running `schema.sql` in MySQL Workbench
   (or let Hibernate create the table automatically with `ddl-auto=update`).
3. Copy `application.properties.template` to `src/main/resources/application.properties`
   and set your MySQL password.
4. Run the app:
   ```
   mvn spring-boot:run
   ```
   The API starts at `http://localhost:8080`.

## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/students` | Get all students (optional filters) |
| GET | `/api/students?department=IT` | Filter by department |
| GET | `/api/students?year=3` | Filter by year |
| GET | `/api/students?semester=5` | Filter by semester |
| GET | `/api/students?department=IT&year=3` | Combined filter |
| GET | `/api/students/{id}` | Get student by ID |
| GET | `/api/students/search?name=Arun` | Search by name (partial, case-insensitive) |
| GET | `/api/students/search?registerNo=23IT001` | Search by register number |
| POST | `/api/students` | Add a student |
| PUT | `/api/students/{id}` | Update a student |
| DELETE | `/api/students/{id}` | Delete a student |

### Sample request (POST /api/students)
```json
{
  "registerNo": "23IT001",
  "name": "Arun Kumar",
  "email": "arun@example.com",
  "phone": "9876543210",
  "department": "IT",
  "year": 3,
  "semester": 5
}
```

## Validation Rules
- Register number, name, department: required
- Email: valid format
- Phone: valid 10-digit Indian number (starts with 6-9)
- Year: 1 to 4
- Semester: 1 to 8

## Error Responses
**409 Conflict** (duplicate register number)
```json
{ "status": 409, "message": "Register number already exists" }
```
**404 Not Found**
```json
{ "status": 404, "message": "Student not found with id: 999" }
```
**400 Bad Request** (validation failed)
```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": { "name": "Name cannot be empty" }
}
```

## Project Structure
```
src/main/java/com/example/student_management
 ├── controller   (StudentController)
 ├── entity       (Student)
 ├── exception    (GlobalExceptionHandler, StudentNotFoundException)
 ├── repository   (StudentRepository)
 └── service      (StudentService)
```

## API Documentation
Postman screenshots are in the `screenshots/` folder and the Postman collection is in `postman/`.
