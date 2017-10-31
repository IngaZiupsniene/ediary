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
    public List<Schoolsubject> classlist() {
        return iSchoolSubjectRep.findAll();
    }

    @Override
    public Schoolsubject save(Schoolsubject schoolsubject) {
        return iSchoolSubjectRep.save(schoolsubject);
    }
}
