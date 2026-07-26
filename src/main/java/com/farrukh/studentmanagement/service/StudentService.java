package com.farrukh.studentmanagement.service;

import com.farrukh.studentmanagement.dto.PagedStudentResponse;
import com.farrukh.studentmanagement.dto.StudentRequest;
import com.farrukh.studentmanagement.dto.StudentResponse;
import com.farrukh.studentmanagement.entity.Student;
import com.farrukh.studentmanagement.exception.DuplicateEmailException;
import com.farrukh.studentmanagement.exception.DuplicateRollNumberException;
import com.farrukh.studentmanagement.exception.StudentNotFoundException;
import com.farrukh.studentmanagement.repository.StudentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    
    public PagedStudentResponse getAllStudents(int page, int size, String sortBy, String direction) {
    
    Sort sort=Sort.by(Sort.Direction.fromString(direction),sortBy);
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Student> students = studentRepository.findAll(pageable);
    List<StudentResponse> studentResponses = students.getContent()
            .stream()
            .map(this::mapToResponse)
            .toList();

    return new PagedStudentResponse(
            studentResponses,
            students.getNumber(),
            students.getSize(),
            students.getTotalElements(),
            students.getTotalPages(),
            students.isLast()
    );
}

    
    public StudentResponse getStudentById(Long id) {

        Student student = findStudentById(id);

        return mapToResponse(student);
    }

    public StudentResponse updateStudentInfo(
            Long id,
            StudentRequest request
    ) {

        Student existingStudent = findStudentById(id);

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

    public void deleteStudent(Long id) {

        Student existingStudent = findStudentById(id);

        studentRepository.delete(existingStudent);
    }

    private Student findStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "No student exists with id: " + id
                ));
    }


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