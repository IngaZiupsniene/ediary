package demo.service.schoolsubjectname;

import demo.model.SchoolSubjectName;
import demo.repository.ISchoolSubjectNameRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SchoolSubjectNameImp implements ISchoolSubjectNameService {
    @Autowired
    ISchoolSubjectNameRep iSchoolSubjectNameRep;

    @Override
    public List<SchoolSubjectName> subjectnamelist() {
        return iSchoolSubjectNameRep.findAll();
    }

    @Override
    public SchoolSubjectName saveAndFlush(SchoolSubjectName schoolSubjectName) {
        return iSchoolSubjectNameRep.saveAndFlush(schoolSubjectName);
    }
}
