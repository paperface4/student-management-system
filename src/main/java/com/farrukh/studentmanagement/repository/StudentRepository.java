package com.farrukh.studentmanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.farrukh.studentmanagement.entity.Student;
import java.util.List;
import java.util.Optional;
public interface StudentRepository extends JpaRepository<Student, Long> {
   public boolean existsByEmail(String email);
   public boolean existsByRollNumber(String rollNumber);

   public List<Student> findByNameContainingIgnoreCase(String name);

   public Optional<Student> findByRollNumber(String rollNumber);

   public Optional<Student> findStudentByEmail(String email);

   public List<Student> findByNameContainingIgnoreCaseAndEmail(String name, String email);
}
