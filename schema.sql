-- Student Management Database Script
CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;

CREATE TABLE IF NOT EXISTS students (
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    register_no   VARCHAR(255) NOT NULL,
    name          VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    phone         VARCHAR(255) NOT NULL,
    department    VARCHAR(255) NOT NULL,
    `year`        INT          NOT NULL,
    semester      INT          NOT NULL,
    created_at    DATETIME(6),
    updated_at    DATETIME(6),
    PRIMARY KEY (id),
    CONSTRAINT uk_students_register_no UNIQUE (register_no)
);

-- Sample data (optional)
INSERT INTO students (register_no, name, email, phone, department, `year`, semester, created_at, updated_at)
VALUES ('23IT001', 'Arun Kumar', 'arun@example.com', '9876543210', 'IT', 3, 5, NOW(6), NOW(6));
