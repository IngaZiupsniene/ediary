package demo.service.fileuploadService;

import demo.model.Student;
import demo.model.User;
import demo.service.roleService.IRoleService;
import demo.service.schoolclassService.ISchoolClassService;
import demo.service.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Service
public class FileUploadServiceImp implements IFileUploadService {

@Autowired
ISchoolClassService iSchoolClassService;

    @Autowired
    IUserService iUserService;
    @Autowired
    IRoleService iRoleService;

    public List<Student> getListFromFile(String path) {
        List<Student> studentList = new ArrayList<>();
        Scanner readAll;

        try {

            readAll = new Scanner(new File(path));

          String name;
          String surname;
          String personalcode;
          String schoolclass;
          String role;


            while (readAll.hasNextLine()){
                String[] studentmas = readAll.nextLine().split(",");
                name = studentmas[0];
                surname = studentmas[1];
                personalcode=studentmas[2];
               schoolclass=studentmas[3];
               role=studentmas[4];

               Student student= new Student();
               student.setName(name);
               student.setSurname(surname);
               student.setPersonalcode(personalcode);

               student.setSchoolclass( iSchoolClassService.findSchoolclassByName(schoolclass));

                User user = new User();
                user.setUsername(name+surname);
                user.setPassword(personalcode);
                user.setRole(iRoleService.findRoleByName(role));
                user.setId(iUserService.save(user).getId());
                student.setUser(user);

                studentList.add(student);
    }

        } catch (FileNotFoundException e) {
            System.out.println("Failo nuskaitymo klaida"+e);
        }
        return studentList;
    }
}
