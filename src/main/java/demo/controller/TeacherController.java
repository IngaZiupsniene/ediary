package demo.controller;

import demo.model.Schoolclass;
import demo.model.Schoolsubject;
import demo.model.Teacher;
import demo.service.schoolclassService.ISchoolClassService;
import demo.service.schoolsubjectService.ISchoolsubjectService;
import demo.service.teacherService.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    ITeacherService iTeacherService;
    @Autowired
    ISchoolClassService iSchoolClassService;
    @Autowired
    ISchoolsubjectService iSchoolsubjectService;


//    @RequestMapping(value = "/main")
//    public String teacherList(){
//        return "teacher";
//    }



    @RequestMapping(value = "/teacherlist", method = RequestMethod.GET)
    public ModelAndView teacherlist() {
        ModelAndView model = new ModelAndView("teacher");
        List<Teacher> teachers = iTeacherService.teacherList();
        model.addObject("teacherlist", teachers);
//        teachers.forEach(c-> System.out.println("subject sarasas"+c.getSubject()));
        List<Schoolclass> schoolclassList=iSchoolClassService.schoolclasslist();
        model.addObject("classlist", schoolclassList);
        return model;
    }


        @RequestMapping(value = "/addteacher", method = RequestMethod.POST)

        public String addTeacher(
                                         @RequestParam (value = "name") String name,
                                 @RequestParam (value = "surname") String surname,
                                 @RequestParam (value = "phone") String phone,
                                 @RequestParam (value = "email") String email,
                                 @RequestParam (value = "schoolclass") Schoolclass schoolclassid,
                                 @RequestParam (value = "subject") String[] subject
                                 ){

        Teacher teacher= new Teacher();
        teacher.setName(name);
        teacher.setSurname(surname);
        teacher.setPhone(phone);
        teacher.setEmail(email);
        teacher.setSchoolclass(schoolclassid);
          Teacher teacher1= iTeacherService.saveandflush(teacher);
            for (String s : subject) {
            Schoolsubject schoolSubject = new Schoolsubject();
            schoolSubject.setName(s);
            schoolSubject.setTeacher(teacher1);
            iSchoolsubjectService.save(schoolSubject);
       }

            return "redirect:/teacherlist";
    }


         @RequestMapping(value = "/teacherdelete", method = RequestMethod.POST)
         @ResponseBody
         public void deleteteacher(@RequestBody Teacher teacher){
              iTeacherService.delete(teacher.getId());

         }





}
