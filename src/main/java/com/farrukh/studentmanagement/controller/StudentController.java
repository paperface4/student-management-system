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

import com.farrukh.studentmanagement.dto.PagedStudentResponse;
import com.farrukh.studentmanagement.dto.StudentRequest;
import com.farrukh.studentmanagement.dto.StudentResponse;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

   @PostMapping("/students")
public StudentResponse registerStudent(
        @Valid @RequestBody StudentRequest request) {

    return studentService.registerStudent(request);
}

    @GetMapping("/students")
public PagedStudentResponse getAllStudents(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
) {
    return studentService.getAllStudents(page, size);
}

    @GetMapping("/students/{id}")
    public StudentResponse getStudentById(@PathVariable  Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/students/{id}")
    public StudentResponse updateStudentInfo(@PathVariable Long id, @RequestBody StudentRequest request) {
        return studentService.updateStudentInfo(id, request);
    }
    
    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }
}