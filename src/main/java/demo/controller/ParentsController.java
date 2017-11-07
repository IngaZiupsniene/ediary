package demo.controller;

import demo.model.*;
import demo.service.parentsService.IParentsService;
import demo.service.studentService.IStudentService;
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
    public String addTeacher(@RequestParam(value = "name") String name,
                             @RequestParam (value = "surname") String surname,
                             @RequestParam (value = "phone") String phone,
                             @RequestParam (value = "email") String email,
                             @RequestParam (value = "adress") String adress,
                             @RequestParam (value = "personalcode") String personalcode,
                             @RequestParam (value = "studentid") long[] studentid){

        Parents parents= new Parents();
        parents.setName(name);
        parents.setSurname(surname);
        parents.setPhone(phone);
        parents.setEmail(email);
        parents.setAdress(adress);
        parents.setPersonalcode(personalcode);


        Parents parents1= iParentsService.saveandflush(parents);

        for (long s : studentid) {
            Student student = new Student();
            student.setId(s);
            student.setParents(parents1);

            iStudentService.save(student);
        }
        return "redirect:/parentslist";
    }

}
