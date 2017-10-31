package demo.repository;

import demo.model.Schoolsubject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISchoolSubjectRep extends JpaRepository<Schoolsubject, Long> {
}
