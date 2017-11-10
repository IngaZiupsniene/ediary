package demo.repository;

import demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStudentRep extends JpaRepository<Student, Long> {
    List<Student> findStudentsByParents_Id(long id);



}
