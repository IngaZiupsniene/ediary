package demo.service.schoolsubjectService;

import demo.model.Schoolsubject;

import java.util.List;

public interface ISchoolsubjectService {
    List<Schoolsubject> classlist();
    Schoolsubject save(Schoolsubject schoolsubject);
}
