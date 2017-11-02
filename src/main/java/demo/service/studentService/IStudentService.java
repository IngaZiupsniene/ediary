package demo.service.studentService;

import demo.model.Student;
import demo.model.Teacher;

import java.util.List;

public interface IStudentService {
    List<Student> studentList();
    Student save(Student student);
    Student findById(long id);
    void delete(long id);
    Student saveandflush(Student student);
}
