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
    public String addparents(@ModelAttribute Parents parents,
                             @RequestParam (value = "studentid") long[] studentid){

        Parents parents1= iParentsService.saveandflush(parents);

        for (long s : studentid) {
            Student student =  iStudentService.findById(s);

            student.setParents(parents1);

            iStudentService.saveandflush(student);
        }
        return "redirect:/parentslist";
    }

}
