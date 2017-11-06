package demo.service.schoolsubjectname;

import demo.model.SchoolSubjectName;
import demo.model.Schoolsubject;

import java.util.List;

public interface ISchoolSubjectNameService {
    List<SchoolSubjectName> subjectnamelist();
    SchoolSubjectName saveAndFlush(SchoolSubjectName schoolSubjectName);
}
