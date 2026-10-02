package com.example.student_management.service;

import com.example.student_management.entity.Student;
import com.example.student_management.exception.StudentNotFoundException;
import com.example.student_management.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> searchStudents(String name, String registerNo) {
        if (name != null && !name.isBlank()) {
            return studentRepository.findByNameContainingIgnoreCase(name);
        }
        if (registerNo != null && !registerNo.isBlank()) {
            return studentRepository.findByRegisterNoContainingIgnoreCase(registerNo);
        }
        return studentRepository.findAll();
    }

    public List<Student> filterStudents(String department, Integer year, Integer semester) {
        return studentRepository.filterStudents(department, year, semester);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getStudentsByDepartment(String department) {
        return studentRepository.findByDepartmentIgnoreCase(department);
    }

    public Student updateStudent(Long id, Student updatedStudent) {

        Student existingStudent = getStudentById(id);

        existingStudent.setRegisterNo(updatedStudent.getRegisterNo());
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setPhone(updatedStudent.getPhone());
        existingStudent.setDepartment(updatedStudent.getDepartment());
        existingStudent.setYear(updatedStudent.getYear());
        existingStudent.setSemester(updatedStudent.getSemester());

        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(Long id) {
        Student existingStudent = getStudentById(id);
        studentRepository.delete(existingStudent);
    }
}