package com.farrukh.studentmanagement.controller;

import com.farrukh.studentmanagement.entity.Student;
import com.farrukh.studentmanagement.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public Student registerStudent( @Valid @RequestBody Student student) {
        return studentService.registerStudent(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable  Long id) {
        return studentService.getstudentById(id);
    }

    @PutMapping("students/{id}")
    public Student updateStudentInfo(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudentInfo(id, student);
    }
    
    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }
}