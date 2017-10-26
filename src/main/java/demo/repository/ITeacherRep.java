package demo.repository;

import demo.model.Role;
import demo.model.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITeacherRep extends JpaRepository<Teacher, Long> {
//    Teacher findById(Teacher teacher); //musu sukurtas metodas -
//  Teacher findByEmailAndAndPhone(String email, String phone);
//    Teacher findBySchoolclass_Name(String classname);


}
