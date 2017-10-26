package demo.service.teacherService;

import demo.model.Teacher;
import demo.repository.ITeacherRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    ITeacherRep iTeacherRep;

    @Override
    public List<Teacher> teacherList() {
        return iTeacherRep.findAll();
    }

    @Override
    public Teacher save(Teacher teacher) { //tinka ir update ir add
      return iTeacherRep.save(teacher);
    }

    @Override
    public Teacher findById(long id) {
        return iTeacherRep.findOne(id);
    }

    @Override
    public void delete(long id) {
        iTeacherRep.delete(id);
    }

}
