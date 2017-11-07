package demo.controller;

import demo.model.SchoolSubjectName;
import demo.model.Schoolclass;
import demo.model.Student;
import demo.model.Teacher;
import demo.service.parentsService.IParentsService;
import demo.service.schoolclassService.ISchoolClassService;
import demo.service.studentService.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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


    @RequestMapping(value = "/addstudent", method = RequestMethod.POST)
    public String addstudent( @ModelAttribute Student student){
        iStudentService.saveandflush(student);
        return "redirect:/studentlist";
    }

    @RequestMapping(value = "/studentdelete", method = RequestMethod.POST)
    @ResponseBody
    public void deletestudent(@RequestBody Student student){
        iStudentService.delete(student.getId());
    }

    @RequestMapping(value="/getonestudent", method = RequestMethod.POST)
    public ModelAndView getonestudent(@RequestParam (value = "getonestudent") long id) {
        ModelAndView model = new ModelAndView("student_update");
        Student student=iStudentService.findById(id);
        model.addObject("onestudent", student);
        List<Schoolclass> schoolclassList=iSchoolClassService.schoolclasslist();
        model.addObject("classlist", schoolclassList);
        return model;
    }
//
//    @RequestMapping(value = "/updatestudent", method = RequestMethod.POST)
//    public String updatestudent( @ModelAttribute Student student){
//        iStudentService.saveandflush(student);
//        return "redirect:/studentlist";
//    }

    @RequestMapping(value = "/updatestudent", method = RequestMethod.POST)
    public String editteacher(@RequestParam (value = "name") String name,
                              @RequestParam (value = "studentid") long studentid,
                              @RequestParam (value = "surname") String surname,
                              @RequestParam (value = "personalcode") String personalcode,
                              @RequestParam (value = "schoolclass") Schoolclass schoolclassid){

        Student student= new Student();
        student.setId(studentid);
        student.setName(name);
        student.setSurname(surname);
        student.setPersonalcode(personalcode);
        student.setSchoolclass(schoolclassid);

        iStudentService.saveandflush(student);
        return "redirect:/studentlist";

    }


}
