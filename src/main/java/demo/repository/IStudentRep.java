package demo.repository;

import demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRep extends JpaRepository<Student, Long> {
}
