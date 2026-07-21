package com.farrukh.studentmanagement.service;

import com.farrukh.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import com.farrukh.studentmanagement.entity.Student;
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

   
public Student registerStudent(Student student) {

    if (student == null) {
        throw new IllegalArgumentException("Student cannot be null");
    }

    if (student.getEmail() == null || student.getEmail().isBlank()) {
        throw new IllegalArgumentException("Email cannot be null or empty");
    }

    if (student.getRollNumber() == null || student.getRollNumber().isBlank()) {
        throw new IllegalArgumentException("Roll number cannot be null or empty");
    }

    if (student.getName() == null || student.getName().isBlank()) {
        throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (student.getCgpa() != null && (student.getCgpa() < 0.0 || student.getCgpa() > 4.0)) {
        throw new IllegalArgumentException("CGPA must be between 0.0 and 4.0");
    }
    if(studentRepository.existsByEmail(student.getEmail())) {
        throw new IllegalArgumentException("Email already exists");
    }
    if(studentRepository.existsByRollNumber(student.getRollNumber())) {
        throw new IllegalArgumentException("Roll number already exists");
    }

    return studentRepository.save(student);
}
}