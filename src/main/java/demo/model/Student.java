package demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ediary_student")
public class Student {
    long id;
    String name;
    String surname;
    Schoolclass schoolclass;
    Parents parents;
    User user;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @ManyToOne
    public Schoolclass getSchoolclass() {
        return schoolclass;
    }

    public void setSchoolclass(Schoolclass schoolclass) {
        this.schoolclass = schoolclass;
    }

    @ManyToOne
    public Parents getParents() {
        return parents;
    }

    public void setParents(Parents parents) {
        this.parents = parents;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
