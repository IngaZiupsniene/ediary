package demo.repository;

import demo.model.Schoolsubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ISchoolSubjectRep extends JpaRepository<Schoolsubject, Long> {
//    Teacher findBySchoolclass_Name(String classname);
    @Transactional
    void deleteSchoolsubjectsByTeacher_Id(long id);

}
