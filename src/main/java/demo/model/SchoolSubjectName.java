package demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ediary_schoolsubjectname")
public class SchoolSubjectName {
    private long id;
    private String name;
   private List<Schoolsubject> schoolsubjectList;

    public SchoolSubjectName() {
        schoolsubjectList=new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "schoolSubjectName")
    public List<Schoolsubject> getSchoolsubjectList() {
        return schoolsubjectList;
    }

    public void setSchoolsubjectList(List<Schoolsubject> schoolsubjectList) {
        this.schoolsubjectList = schoolsubjectList;
    }
}
