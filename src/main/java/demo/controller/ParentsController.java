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

    @RequestMapping(value="/getoneparents", method = RequestMethod.GET)
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
                              @RequestParam (value = "adress") String adress ){

        Parents parents=new Parents();
        parents.setId(parentid);
        parents.setName(name);
        parents.setSurname(surname);
        parents.setPhone(phone);
        parents.setEmail(email);
        parents.setAdress(adress);
        parents.setPersonalcode(personalcode);

        iParentsService.saveandflush(parents);

        return "redirect:/parentslist";

    }



    @RequestMapping(value = "/studentdeletefromparents", method = RequestMethod.POST)
    @ResponseBody
    public void studentdeletefromparents(@RequestBody Student student){

       Student student1= iStudentService.findById(student.getId());
       student1.setParents(null);
       iStudentService.save(student1);
    }


    @RequestMapping(value = "/addnewstudenttoparent", method = RequestMethod.POST)
    public String addparents(@RequestParam (value = "studentid") long[] studentid,
                             @RequestParam (value = "parents_id_to_student") long parents_id_to_student) {

        for (long s : studentid) {
            Student student = iStudentService.findById(s);


            student.setParents(iParentsService.findById(parents_id_to_student));

            iStudentService.saveandflush(student);
        }
        return "redirect:/getoneparents?getoneparents="+parents_id_to_student;
    }

}
