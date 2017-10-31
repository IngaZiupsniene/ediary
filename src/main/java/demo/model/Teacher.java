package demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ediary_teacher")
public class Teacher {
    private long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private List<Schoolsubject> subject;
    private Schoolclass schoolclass;
    private User user;


    public Teacher() {
        subject=new ArrayList<>();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "teacher")
    public List<Schoolsubject> getSubject() {
        return subject;
    }

    public void setSubject(List<Schoolsubject> subject) {
        this.subject = subject;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "schoolclass_id")
    public Schoolclass getSchoolclass() {
        return schoolclass;
    }

    public void setSchoolclass(Schoolclass schoolclass) {
        this.schoolclass = schoolclass;
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
