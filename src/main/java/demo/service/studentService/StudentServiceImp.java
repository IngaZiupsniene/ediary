package demo.service.studentService;

import demo.model.Student;
import demo.repository.IStudentRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements IStudentService{

    @Autowired
    IStudentRep iStudentRep;

    @Override
    public List<Student> studentList() {
        return iStudentRep.findAll();
    }

    @Override
    public Student save(Student student) {
        return iStudentRep.save(student);
    }

    @Override
    public Student findById(long id) {
        return iStudentRep.findOne(id);
    }

    @Override
    public void delete(long id) {
iStudentRep.delete(id);
    }

    @Override
    public Student saveandflush(Student student) {
        return iStudentRep.saveAndFlush(student);
    }

    @Override
    public List<Student> findStudentsByParents_Id(long id) {
        return iStudentRep.findStudentsByParents_Id(id);
    }
}
