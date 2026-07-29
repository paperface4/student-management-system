package com.farrukh.studentmanagement.dto;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class StudentRequest {
    
    @NotBlank(message = "name cannot be blank")
    @Size(min = 8, max = 20, message = "Name must be between 8 and 20 characters")
    private String name;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email has to be in valid format") 
    private String email;
    @NotBlank(message = "Roll number cannot be blank")
    private String rollNumber;
    private String department;
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 12, message = "Semester cannot be greater than 12")
    @NotNull(message = "Semester is required")
    private Integer semester;
    @DecimalMax(value = "4.0", message = "CGPA cannot be greater than 4.0")
    @DecimalMin(value = "0.0", message = "CGPA cannot be less than 0.0")
    private Float cgpa;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRollNumber() {
        return rollNumber;
    }
    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public Integer getSemester() {
        return semester;
    }
    public void setSemester(Integer semester) {
        this.semester = semester;
    }
    public Float getCgpa() {
        return cgpa;
    }
    public void setCgpa(Float cgpa) {
        this.cgpa = cgpa;
    }
}
