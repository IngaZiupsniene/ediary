package demo.controller;

import demo.model.Schoolclass;
import demo.model.Student;
import demo.model.Teacher;
import demo.service.parentsService.IParentsService;
import demo.service.schoolclassService.ISchoolClassService;
import demo.service.studentService.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    ISchoolClassService iSchoolClassService;
    @Autowired
    IStudentService iStudentService;
    @Autowired
    IParentsService iParentsService;

    @RequestMapping(value = "/studentlist", method = RequestMethod.GET)
    public ModelAndView studentlist() {
        ModelAndView model = new ModelAndView("student");
        List<Student> studentList = iStudentService.studentList();
        model.addObject("studlist", studentList);
        List<Schoolclass> schoolclassList=iSchoolClassService.schoolclasslist();
        model.addObject("classlist", schoolclassList);

        return model;
    }
}
