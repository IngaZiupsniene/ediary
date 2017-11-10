package demo.service.schoolclassService;

import demo.model.Schoolclass;
import demo.repository.ISchoolClassRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolclassServiceImpl implements ISchoolClassService{
    @Autowired
    ISchoolClassRep iSchoolClassRep;



    @Override
    public List<Schoolclass> schoolclasslist() {
        return iSchoolClassRep.findAll();
    }

    @Override
    public Schoolclass findSchoolclassByName(String classname) {
        return iSchoolClassRep.findSchoolclassByName(classname);
    }

}
