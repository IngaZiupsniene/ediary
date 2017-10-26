package demo.controller;

import demo.model.Teacher;
import demo.service.teacherService.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    ITeacherService iTeacherService;


    @RequestMapping(value = "/teacherlist")
    public String teacherList(){
        return "teacher";
    }



    @RequestMapping(value = "/teacherlist", method = RequestMethod.GET)
    public ModelAndView studList(){
        ModelAndView model=new ModelAndView("teacher");
        List<Teacher> teachers=iTeacherService.teacherList();
        model.addObject("teacherlist", teachers);
        return model;
    }
}
