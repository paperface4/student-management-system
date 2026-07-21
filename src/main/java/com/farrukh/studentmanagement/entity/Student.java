
package com.farrukh.studentmanagement.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String rollNumber;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String department;
    private Integer semester;
    private LocalDate dateOfBirth;
    private Float cgpa;
}
