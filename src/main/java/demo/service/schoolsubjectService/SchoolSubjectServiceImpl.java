package demo.service.schoolsubjectService;

import demo.model.Schoolsubject;
import demo.repository.ISchoolSubjectRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolSubjectServiceImpl implements ISchoolsubjectService{

    @Autowired
    ISchoolSubjectRep iSchoolSubjectRep;

    @Override
    public List<Schoolsubject> subjectlist() {
        return iSchoolSubjectRep.findAll();
    }




    @Override
    public Schoolsubject saveAndFlush(Schoolsubject schoolsubject) {
        return iSchoolSubjectRep.saveAndFlush(schoolsubject);
    }

    @Override
    public void delete(long id) {
        iSchoolSubjectRep.delete(id);
    }

    @Override
    public void deleteSchoolsubjectsByTeacher_Id(long id) {
        iSchoolSubjectRep.deleteSchoolsubjectsByTeacher_Id(id);
    }
}
