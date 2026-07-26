package com.farrukh.studentmanagement.service;

import com.farrukh.studentmanagement.dto.StudentRequest;
import com.farrukh.studentmanagement.dto.StudentResponse;
import com.farrukh.studentmanagement.entity.Student;
import com.farrukh.studentmanagement.exception.DuplicateEmailException;
import com.farrukh.studentmanagement.exception.DuplicateRollNumberException;
import com.farrukh.studentmanagement.exception.StudentNotFoundException;
import com.farrukh.studentmanagement.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Register a new student
    public StudentResponse registerStudent(StudentRequest request) {

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        if (studentRepository.existsByRollNumber(request.getRollNumber())) {
            throw new DuplicateRollNumberException("Roll number already exists");
        }

        Student student = mapToEntity(request);

        Student savedStudent = studentRepository.save(student);

        return mapToResponse(savedStudent);
    }

    // Get all students
    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get one student by ID
    public StudentResponse getStudentById(Long id) {

        Student student = findStudentById(id);

        return mapToResponse(student);
    }

    // Update student information
    public StudentResponse updateStudentInfo(
            Long id,
            StudentRequest request
    ) {

        Student existingStudent = findStudentById(id);

        /*
         * Only throw duplicate email exception when another student
         * already owns the requested email.
         */
        if (!existingStudent.getEmail().equals(request.getEmail())
                && studentRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateEmailException("Email already exists");
        }

        if (!existingStudent.getRollNumber().equals(request.getRollNumber())
                && studentRepository.existsByRollNumber(request.getRollNumber())) {

            throw new DuplicateRollNumberException(
                    "Roll number already exists"
            );
        }

        existingStudent.setName(request.getName());
        existingStudent.setEmail(request.getEmail());
        existingStudent.setRollNumber(request.getRollNumber());
        existingStudent.setDepartment(request.getDepartment());
        existingStudent.setDateOfBirth(request.getDateOfBirth());
        existingStudent.setSemester(request.getSemester());
        existingStudent.setCgpa(request.getCgpa());

        Student updatedStudent = studentRepository.save(existingStudent);

        return mapToResponse(updatedStudent);
    }

    // Delete student
    public void deleteStudent(Long id) {

        Student existingStudent = findStudentById(id);

        studentRepository.delete(existingStudent);
    }

    // Reusable method for finding a student
    private Student findStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "No student exists with id: " + id
                ));
    }

    // Convert StudentRequest DTO into Student entity
    private Student mapToEntity(StudentRequest request) {

        Student student = new Student();

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setRollNumber(request.getRollNumber());
        student.setDepartment(request.getDepartment());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setSemester(request.getSemester());
        student.setCgpa(request.getCgpa());

        return student;
    }

    // Convert Student entity into StudentResponse DTO
    private StudentResponse mapToResponse(Student student) {

        StudentResponse response = new StudentResponse();

        response.setId(student.getId());
        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setRollNumber(student.getRollNumber());
        response.setDepartment(student.getDepartment());
        response.setDateOfBirth(student.getDateOfBirth());
        response.setSemester(student.getSemester());
        response.setCgpa(student.getCgpa());

        return response;
    }
}