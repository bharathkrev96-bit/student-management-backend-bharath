package com.example.student_management.repository;

import com.example.student_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByDepartmentIgnoreCase(String department);

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByRegisterNoContainingIgnoreCase(String registerNo);

    @Query("SELECT s FROM Student s WHERE " +
            "(:department IS NULL OR LOWER(s.department) = LOWER(:department)) AND " +
            "(:year IS NULL OR s.year = :year) AND " +
            "(:semester IS NULL OR s.semester = :semester)")
    List<Student> filterStudents(@Param("department") String department,
                                 @Param("year") Integer year,
                                 @Param("semester") Integer semester);

}