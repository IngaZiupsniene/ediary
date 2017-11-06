package demo.service.schoolsubjectService;

import demo.model.Schoolsubject;

import java.util.List;

public interface ISchoolsubjectService {
    List<Schoolsubject> subjectlist();
    Schoolsubject saveAndFlush(Schoolsubject schoolsubject);
    void delete(long id);
    void deleteSchoolsubjectsByTeacher_Id(long id);
}
