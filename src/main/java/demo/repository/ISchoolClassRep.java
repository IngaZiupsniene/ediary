package demo.repository;

import demo.model.Schoolclass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISchoolClassRep extends JpaRepository<Schoolclass, Long> {

}
