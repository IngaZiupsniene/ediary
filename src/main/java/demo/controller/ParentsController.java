package demo.controller;

import demo.model.*;
import demo.service.parentsService.IParentsService;
import demo.service.studentService.IStudentService;
import demo.service.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ParentsController {
    @Autowired
    IStudentService iStudentService;
    @Autowired
    IParentsService iParentsService;
    @Autowired
    IUserService iUserService;

    @RequestMapping(value = "/parentslist", method = RequestMethod.GET)
    public ModelAndView parentslist() {
        ModelAndView model = new ModelAndView("parents");
        List<Parents> parentsList = iParentsService.parentList();
        model.addObject("parentlist", parentsList);
        List<Student> studentList = iStudentService.studentList();
        model.addObject("studlist", studentList);
        return model;
    }

    @RequestMapping(value = "/parentsdelete", method = RequestMethod.POST)
    @ResponseBody
    public void deleteparents(@RequestBody Parents parents){

        iParentsService.delete(parents.getId());
    }


    @RequestMapping(value = "/addsparents", method = RequestMethod.POST)
    public String addparents(@ModelAttribute Parents parents,
                             @RequestParam(value = "role") long roleid,
                             @RequestParam (value = "studentid") long[] studentid){

        User user = new User();
        user.setUsername(parents.getName()+parents.getSurname());
        user.setPassword(parents.getPersonalcode());
        Role role = new Role();
        role.setId(roleid);
        user.setRole(role);

        user.setId(iUserService.save(user).getId());
        parents.setUser(user);

        Parents parents1= iParentsService.saveandflush(parents);

        for (long s : studentid) {
            Student student =  iStudentService.findById(s);

            student.setParents(parents1);

            iStudentService.saveandflush(student);
        }
        return "redirect:/parentslist";
    }

    @RequestMapping(value="/getoneparents", method = RequestMethod.POST)
    public ModelAndView getoneparent(@RequestParam (value = "getoneparents") long id) {
        ModelAndView model = new ModelAndView("parents_update");
        Parents parents=iParentsService.findById(id);
        model.addObject("oneparents", parents);
        List<Student> studentList=iStudentService.studentList();
        model.addObject("studentlist", studentList);

        return model;
    }

    @RequestMapping(value = "/updateparents", method = RequestMethod.POST)
    public String editteacher(@RequestParam (value = "name") String name,
                              @RequestParam (value = "parentid") long parentid,
                              @RequestParam (value = "surname") String surname,
                              @RequestParam (value = "personalcode") String personalcode,
                              @RequestParam (value = "phone") String phone,
                              @RequestParam (value = "email") String email,
                              @RequestParam (value = "adress") String adress,
                              @RequestParam (value = "student_update") long[] student_update,
                              @RequestParam (value = "student_update_new") long[] student_update_new){

        Parents parents=new Parents();
        parents.setId(parentid);
        parents.setName(name);
        parents.setSurname(surname);
        parents.setPhone(phone);
        parents.setEmail(email);
        parents.setAdress(adress);
        parents.setPersonalcode(personalcode);

        iParentsService.saveandflush(parents);



if(student_update_new!=null){
    for(long stud_new:student_update_new){
        for (long stud_old:student_update){
            if (stud_new!=stud_old){
                Student  student_old =  iStudentService.findById(stud_old);
                student_old.setParents(null);
                Student  student =  iStudentService.findById(stud_new);
                student.setParents(parents);
                iStudentService.saveandflush(student);
            }
//            student =  iStudentService.findById(stud_old);
//            student.setParents(parents);
//            iStudentService.saveandflush(student);
        }

    }
}


        return "redirect:/parentslist";

    }


}
