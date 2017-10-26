package demo.service.teacherService;


import demo.model.Teacher;

import java.util.List;

public interface ITeacherService {
    List<Teacher> teacherList();
    Teacher save(Teacher teacher);
    Teacher findById(long id);
    void delete(long id);

}
