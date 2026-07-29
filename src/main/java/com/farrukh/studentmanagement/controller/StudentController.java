package com.farrukh.studentmanagement.controller;

import com.farrukh.studentmanagement.dto.PagedStudentResponse;
import com.farrukh.studentmanagement.dto.StudentRequest;
import com.farrukh.studentmanagement.dto.StudentResponse;
import com.farrukh.studentmanagement.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(
            summary = "Create a new student",
            description = "Creates a student record using the provided details."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Student created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed because the request contains invalid data"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "A student with the provided email or roll number already exists"
            )
    })
    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse registerStudent( @Valid @RequestBody StudentRequest request) {

        return studentService.registerStudent(request);
    }

    @Operation(
            summary = "Get all students",
            description = "Retrieves a paginated list of all students with optional sorting."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the paginated list of students"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid pagination or sorting parameters"
            )
    })
    @GetMapping("/students")
    public PagedStudentResponse getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return studentService.getAllStudents(page, size, sortBy, direction);
    }

    @Operation(
            summary = "Get student by roll number",
            description = "Retrieves a student record using the provided roll number."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the student"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No student was found with the provided roll number"
            )
    })
    @GetMapping("/students/rollNumber/{rollNumber}")
    public StudentResponse getStudentByRollNumber(
            @PathVariable String rollNumber) {

        return studentService.getStudentByRollNumber(rollNumber);
    }

    @Operation(
            summary = "Get student by email",
            description = "Retrieves a student record using the provided email address."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the student"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No student was found with the provided email address"
            )
    })
    @GetMapping("/students/email/{email}")
    public StudentResponse findStudentByEmail(
            @PathVariable String email) {

        return studentService.findStudentByEmail(email);
    }

    @Operation(
            summary = "Get student by ID",
            description = "Retrieves a student record using the provided ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the student"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No student was found with the provided ID"
            )
    })
    @GetMapping("/students/{id}")
    public StudentResponse getStudentById(
            @PathVariable Long id) {

        return studentService.getStudentById(id);
    }

    @Operation(
            summary = "Search students",
            description = "Searches for students by name, email, or both. Both parameters are optional."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the search results"
    )
    @GetMapping("/students/search")
    public List<StudentResponse> searchStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {

        return studentService.searchStudents(name, email);
    }

    @Operation(
            summary = "Update student information",
            description = "Updates the student identified by the provided ID using the supplied details."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Student information updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed because the request contains invalid data"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No student was found with the provided ID"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "The updated email or roll number is already used by another student"
            )
    })
    @PutMapping("/students/{id}")
    public StudentResponse updateStudentInfo(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest request) {

        return studentService.updateStudentInfo(id, request);
    }

    @Operation(
            summary = "Delete a student",
            description = "Deletes the student record identified by the provided ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Student deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No student was found with the provided ID"
            )
    })
    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);
    }
}