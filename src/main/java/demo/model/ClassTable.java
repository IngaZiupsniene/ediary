package demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ediary_classtable")
public class ClassTable {
    private long id;
    private List<ClassListTable> classListTables;
    private int grade;
    private String attendance;
    private List<Student> studentList;

    public ClassTable() {
        classListTables=new ArrayList<>();
        studentList=new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "classTable")
    public List<ClassListTable> getClassListTables() {
        return classListTables;
    }

    public void setClassListTables(List<ClassListTable> classListTables) {
        this.classListTables = classListTables;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    @OneToMany(mappedBy = "classTableinstudent")
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
