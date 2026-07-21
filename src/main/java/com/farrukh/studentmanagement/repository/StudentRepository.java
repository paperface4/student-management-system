package com.farrukh.studentmanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.farrukh.studentmanagement.entity.Student;
public interface StudentRepository extends JpaRepository<Student, Long> {
   public boolean existsByEmail(String email);
   public boolean existsByRollNumber(String rollNumber);
}
