package com.farrukh.studentmanagement.dto;

import java.time.LocalDate;

public class StudentResponse {
    private Long id;
    private String rollNumber;
    private String name;
    private String email;
    private String department;
    private Integer semester;
    private LocalDate dateOfBirth;
    private Float cgpa;

    public Long getId() {
        return id;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public Integer getSemester() {
        return semester;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Float getCgpa() {
        return cgpa;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void setSemester(Integer semester) {
        this.semester = semester;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setCgpa(Float cgpa) {
        this.cgpa = cgpa;
    }
}
