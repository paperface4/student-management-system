
package com.farrukh.studentmanagement.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Roll number cannot be blank")
    @Column(unique = true, nullable = false)
    private String rollNumber;
    @NotBlank(message = "Name cannot be blank")
    @Column(nullable = false)
    private String name;
    @NotBlank(message = "Email cannot be blank")
    @Email(message="Email has to be in valid formate")
    @Column(unique = true, nullable = false)
    private String email;
    private String department;
    @NotNull(message = "Semester cannot be null")
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 12, message = "Semester cannot be greater than 8")
    private Integer semester;
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    @DecimalMax(value = "4.0", message = "CGPA cannot be greater than 4.0")
    @DecimalMin(value = "0.0", message = "CGPA cannot be less than 0.0")
    private Float cgpa;

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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public Integer getSemester() {
        return semester;
    }
    public void setSemester(Integer semester) {
        this.semester = semester;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public Float getCgpa() {
        return cgpa;
    }
    public void setCgpa(Float cgpa) {
        this.cgpa = cgpa;
    }
}
