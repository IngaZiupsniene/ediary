package demo.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "ediary_schoolsubject")
public class Schoolsubject {
    private long id;
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
