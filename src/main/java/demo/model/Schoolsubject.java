package demo.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "ediary_schoolsubject")
public class Schoolsubject {
    private long id;
    private SchoolSubjectName schoolSubjectName;
    private Teacher teacher;



    public Schoolsubject() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    public SchoolSubjectName getSchoolSubjectName() {
        return schoolSubjectName;
    }

    public void setSchoolSubjectName(SchoolSubjectName schoolSubjectName) {
        this.schoolSubjectName = schoolSubjectName;
    }

    @ManyToOne
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
