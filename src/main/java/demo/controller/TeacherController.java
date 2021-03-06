package demo.controller;

import demo.model.*;
import demo.service.roleService.IRoleService;
import demo.service.schoolclassService.ISchoolClassService;
import demo.service.schoolsubjectService.ISchoolsubjectService;
import demo.service.schoolsubjectname.ISchoolSubjectNameService;
import demo.service.teacherService.ITeacherService;
import demo.service.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.Subject;
import java.util.ArrayList;
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
     IUserService iUserService;


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
                                 @RequestParam (value = "subject") long[] subject,
                                 @RequestParam(value = "role") long roleid
                                 ){

            User user = new User();
            user.setUsername(name+surname);
            user.setPassword(personalcode);
            Role role = new Role();
            role.setId(roleid);
            user.setRole(role);

           user.setId(iUserService.save(user).getId());


        Teacher teacher= new Teacher();
        teacher.setName(name);
        teacher.setSurname(surname);
        teacher.setPhone(phone);
        teacher.setEmail(email);
        teacher.setUser(user);

        teacher.setPersonalcode(personalcode);
        teacher.setSchoolclass(schoolclassid);

          Teacher teacher1= iTeacherService.saveandflush(teacher);

            addshcoolsubject(subject,teacher1);

            return "redirect:/teacherlist";
    }


         @RequestMapping(value = "/teacherdelete", method = RequestMethod.POST)
         @ResponseBody
         public void deleteteacher(@RequestBody Teacher teacher){
              iTeacherService.delete(teacher.getId());

         }

    @RequestMapping(value="/getoneteacher", method = RequestMethod.GET)
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


    @RequestMapping(value = "/updateteacher", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded"})
    public String editteacher(@ModelAttribute Teacher teacher,
//                              @RequestParam (value = "subject_update_id") long[] subjectid,
                              @RequestParam(value = "teacherid") long teacherid,
                              @RequestParam (value = "schoolclass") Schoolclass schoolclass,
                              @RequestParam (value = "schoolclass_new") Schoolclass schoolclass_newid){

        teacher.setId(teacherid);
        if(schoolclass_newid!=schoolclass){
            teacher.setSchoolclass(schoolclass_newid);
        }else teacher.setSchoolclass(schoolclass);

        Teacher teacher1= iTeacherService.saveandflush(teacher);


//         iSchoolsubjectService.deleteSchoolsubjectsByTeacher_Id(teacher.getId());
//
//        addshcoolsubject(subjectid, teacher1);

        return "redirect:/teacherlist";
    }


    public void addshcoolsubject(long[] subjectid, Teacher teacher){
        for (long s : subjectid) {
            SchoolSubjectName schoolSubjectName = new SchoolSubjectName();
            schoolSubjectName.setId(s);

            Schoolsubject schoolSubject = new Schoolsubject();
            schoolSubject.setSchoolSubjectName(schoolSubjectName);
            schoolSubject.setTeacher(teacher);
            iSchoolsubjectService.saveAndFlush(schoolSubject);
        }
    }



    @RequestMapping(value = "/subjectdeletefromteacher", method = RequestMethod.POST)
    @ResponseBody
    public void subjectdeletefromteacher(@RequestBody Schoolsubject subject){

        Schoolsubject schoolsubject= iSchoolsubjectService.findById(subject.getId());
//        schoolsubject.setSchoolSubjectName(null);
        iSchoolsubjectService.delete(schoolsubject.getId());
    }


    @RequestMapping(value = "/addnewsubjecttoteacher", method = RequestMethod.POST)
    public String addparents(@RequestParam (value = "subjectid") long[] subjectnameid,
                             @RequestParam (value = "teacher_id_to_subject") long teacher_id_to_subject) {
        List<Long> list=new ArrayList<>();
        for(Long a:subjectnameid){
            System.out.println(list.add(a));
        }



        for (long s : subjectnameid) {
            SchoolSubjectName schoolSubjectName=new SchoolSubjectName();
            schoolSubjectName.setId(s);


            Schoolsubject schoolsubject = new Schoolsubject();
            schoolsubject.setSchoolSubjectName(schoolSubjectName);

            schoolsubject.setTeacher(iTeacherService.findById(teacher_id_to_subject));

            iSchoolsubjectService.saveAndFlush(schoolsubject);
        }
        return "redirect:/getoneteacher?getoneteacher="+teacher_id_to_subject;
    }


}
