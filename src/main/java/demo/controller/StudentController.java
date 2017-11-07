package demo.controller;

import demo.model.*;
import demo.service.parentsService.IParentsService;
import demo.service.schoolclassService.ISchoolClassService;
import demo.service.studentService.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        List<Parents> parentsList=iParentsService.parentList();
        model.addObject("parentlist", parentsList);
        return model;
    }


    @RequestMapping(value = "/updatestudent", method = RequestMethod.POST)
    public String editteacher(@RequestParam (value = "name") String name,
                              @RequestParam (value = "studentid") long studentid,
                              @RequestParam (value = "surname") String surname,
                              @RequestParam (value = "personalcode") String personalcode,
                              @RequestParam (value = "parents") Parents parents,
                              @RequestParam (value = "parents_new") Parents parents_new,
                              @RequestParam (value = "schoolclass") Schoolclass schoolclass,
                              @RequestParam (value = "schoolclass_new") Schoolclass schoolclass_newid){

        Student student= new Student();
        student.setId(studentid);
        student.setName(name);
        student.setSurname(surname);
        student.setPersonalcode(personalcode);
        if(schoolclass_newid!=schoolclass){
            student.setSchoolclass(schoolclass_newid);
        }else student.setSchoolclass(schoolclass);
        if(parents_new!=parents){
            student.setParents(parents_new);
        }else student.setParents(parents);

        iStudentService.saveandflush(student);
        return "redirect:/studentlist";

    }




}
