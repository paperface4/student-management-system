package com.farrukh.studentmanagement.service;

import com.farrukh.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import com.farrukh.studentmanagement.entity.Student;
import java.util.List;

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

public List<Student> getAllStudents(){
    return studentRepository.findAll();
}

public Student getstudentById(Long ID){
    return studentRepository.findById(ID). orElseThrow(()->new RuntimeException("student doesn't exits with id "+ID));
}

public Student updateStudentInfo(Long id,Student student){
   Student existingStudent=studentRepository.findById(id). orElseThrow(()->new RuntimeException("student doesn't exits with id "+id));
   existingStudent.setName(student.getName());
   existingStudent.setEmail(student.getEmail());
   existingStudent.setRollNumber(student.getRollNumber());
   existingStudent.setCgpa(student.getCgpa());
   existingStudent.setDepartment(student.getDepartment());
   existingStudent.setDateOfBirth(student.getDateOfBirth());
   return studentRepository.save(existingStudent);
}

public void deleteStudent(Long Id){
    Student existingStudent=studentRepository.findById(Id). orElseThrow(()->new RuntimeException("student doesn't exits with id "+Id));
    studentRepository.delete(existingStudent);
}
}