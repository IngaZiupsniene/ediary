package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AttendanceController {

    @RequestMapping(value = "/attendance", method = RequestMethod.GET)
    public ModelAndView parentslist() {
        ModelAndView model = new ModelAndView("parents");
        List<Parents> parentsList = iParentsService.parentList();
        model.addObject("parentlist", parentsList);
        List<Student> studentList = iStudentService.studentList();
        model.addObject("studlist", studentList);
        return model;
    }

}
