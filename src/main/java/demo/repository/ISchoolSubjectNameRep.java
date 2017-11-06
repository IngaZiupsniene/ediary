package demo.repository;

import demo.model.SchoolSubjectName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISchoolSubjectNameRep extends JpaRepository<SchoolSubjectName, Long> {
}
