package demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ediary_student")
public class Student {
    private long id;
    private String name;
    private String surname;
    private Schoolclass schoolclass;
    private Parents parents;
    private User user;
    private String personalcode;

    public Student() {
    }

    public Student(String name, String surname, String personalcode) {
        this.name = name;
        this.surname = surname;
        this.personalcode = personalcode;
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

    public String getPersonalcode() {
        return personalcode;
    }

    public void setPersonalcode(String personalcode) {
        this.personalcode = personalcode;
    }
}
