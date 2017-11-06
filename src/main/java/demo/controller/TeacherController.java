package demo.controller;

import demo.model.*;
import demo.service.roleService.IRoleService;
import demo.service.schoolclassService.ISchoolClassService;
import demo.service.schoolsubjectService.ISchoolsubjectService;
import demo.service.schoolsubjectname.ISchoolSubjectNameService;
import demo.service.teacherService.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    ITeacherService iTeacherService;
    @Autowired
    ISchoolClassService iSchoolClassService;
    @Autowired
    ISchoolsubjectService iSchoolsubjectService;
    @Autowired
    ISchoolSubjectNameService iSchoolSubjectNameService;
    @Autowired
    IRoleService iRoleService;
    @Autowired
    JdbcTemplate jdbcTemplate;


    @RequestMapping(value = "/teacherlist", method = RequestMethod.GET)
    public ModelAndView teacherlist() {
        ModelAndView model = new ModelAndView("teacher");
        List<Teacher> teachers = iTeacherService.teacherList();
        model.addObject("teacherlist", teachers);
        List<Schoolclass> schoolclassList=iSchoolClassService.schoolclasslist();
        model.addObject("classlist", schoolclassList);

        List<SchoolSubjectName> schoolSubjectNameList=iSchoolSubjectNameService.subjectnamelist();
        model.addObject("schoolsubjectlist", schoolSubjectNameList);

        List<Role> roleList=iRoleService.roleList();
        model.addObject("rolelist", roleList);

        return model;
    }

        @RequestMapping(value = "/addteacher", method = RequestMethod.POST)
        public String addTeacher(@RequestParam (value = "name") String name,
                                 @RequestParam (value = "surname") String surname,
                                 @RequestParam (value = "phone") String phone,
                                 @RequestParam (value = "email") String email,
                                 @RequestParam (value = "personalcode") String personalcode,
                                 @RequestParam (value = "schoolclass") Schoolclass schoolclassid,
                                 @RequestParam (value = "subject") long[] subject
                                 ){

        Teacher teacher= new Teacher();
        teacher.setName(name);
        teacher.setSurname(surname);
        teacher.setPhone(phone);
        teacher.setEmail(email);

        teacher.setPersonalcode(personalcode);
        teacher.setSchoolclass(schoolclassid);

          Teacher teacher1= iTeacherService.saveandflush(teacher);

            for (long s : subject) {
                SchoolSubjectName schoolSubjectName= new SchoolSubjectName();
                schoolSubjectName.setId(s);


            Schoolsubject schoolSubject = new Schoolsubject();
            schoolSubject.setSchoolSubjectName(schoolSubjectName);
            schoolSubject.setTeacher(teacher1);
            iSchoolsubjectService.saveAndFlush(schoolSubject);
       }
            return "redirect:/teacherlist";
    }


         @RequestMapping(value = "/teacherdelete", method = RequestMethod.POST)
         @ResponseBody
         public void deleteteacher(@RequestBody Teacher teacher){
              iTeacherService.delete(teacher.getId());

         }

    @RequestMapping(value="/getoneteacher", method = RequestMethod.POST)
    public ModelAndView getoneteacher(@RequestParam (value = "getoneteacher") long id) {
        ModelAndView model = new ModelAndView("teacher_update");
        Teacher teacher1=iTeacherService.findById(id);
        model.addObject("oneteacher", teacher1);
        List<SchoolSubjectName> schoolSubjectNameList=iSchoolSubjectNameService.subjectnamelist();
        model.addObject("schoolsubjectlist", schoolSubjectNameList);
        List<Schoolclass> schoolclassList=iSchoolClassService.schoolclasslist();
        model.addObject("classlist", schoolclassList);
             return model;
    }
//
    @RequestMapping(value = "/updateteacher", method = RequestMethod.POST)
    public String editteacher(@RequestParam (value = "name") String name,
                            @RequestParam (value = "teacherid") long teacherid,
                            @RequestParam (value = "surname") String surname,
                            @RequestParam (value = "phone") String phone,
                            @RequestParam (value = "email") String email,
                            @RequestParam (value = "personalcode") String personalcode,
                            @RequestParam (value = "schoolclass") Schoolclass schoolclassid,
                            @RequestParam (value = "subject") long[] subject ){

        Teacher teacher= new Teacher();
        teacher.setId(teacherid);
        teacher.setName(name);
        teacher.setSurname(surname);
        teacher.setPhone(phone);
        teacher.setEmail(email);
        teacher.setPersonalcode(personalcode);
        teacher.setSchoolclass(schoolclassid);

         iTeacherService.saveandflush(teacher);

         String sqldelete="DELETE from ediary_schoolsubject WHERE teacher_id=?";
         jdbcTemplate.update(sqldelete, teacherid);

//         iSchoolsubjectService.deleteSchoolsubjectsByTeacher_Id(teacherid);

        for (long s : subject) {
            SchoolSubjectName schoolSubjectName= new SchoolSubjectName();
            schoolSubjectName.setId(s);


            Schoolsubject schoolSubject = new Schoolsubject();
            schoolSubject.setSchoolSubjectName(schoolSubjectName);
            schoolSubject.setTeacher(teacher);
            iSchoolsubjectService.saveAndFlush(schoolSubject);
        }
        return "redirect:/teacherlist";

    }




}
