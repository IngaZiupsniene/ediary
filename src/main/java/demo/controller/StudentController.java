package demo.controller;

import demo.model.*;
import demo.service.fileuploadService.IFileUploadService;
import demo.service.parentsService.IParentsService;
import demo.service.schoolclassService.ISchoolClassService;
import demo.service.studentService.IStudentService;
import demo.service.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    ISchoolClassService iSchoolClassService;
    @Autowired
    IStudentService iStudentService;
    @Autowired
    IParentsService iParentsService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IFileUploadService iFileUploadService;

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
    public String addstudent( @ModelAttribute Student student, @RequestParam(value = "role") long roleid){

        User user = new User();
        user.setUsername(student.getName()+student.getSurname());
        user.setPassword(student.getPersonalcode());
        Role role = new Role();
        role.setId(roleid);
        user.setRole(role);

        user.setId(iUserService.save(user).getId());
        student.setUser(user);
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

    @RequestMapping(value = "/studentfromfile", method = RequestMethod.GET)
    public String studentfromfile() {
        return "student_upload_file";
    }


    @RequestMapping(value = "/studentfileupload", method = RequestMethod.POST)
    public String singleFileUpload(@RequestParam("file") MultipartFile file, Model model) {

              String upload_path = "C:\\Users\\Dainius\\IdeaProjects\\A_Projektas\\ediary\\src\\main\\webapp\\resource\\csv\\";

        if (file.isEmpty()) {
            model.addAttribute("message", "Prašome pasirinkti failą");
        }
        try {

            byte[] bytes = file.getBytes();
            String filename  = file.getOriginalFilename();
            Path path = Paths.get(upload_path + filename);
            Files.write(path, bytes);

            List<Student> studentList = iFileUploadService.getListFromFile(upload_path+filename);

            iStudentService.savelist(studentList);

            model.addAttribute("message", "Duomenys iš failo "+file.getOriginalFilename()+" sėkmingai pridėti");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "student_upload_file";
    }





}
