package demo.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ediary_classlisttable")
public class ClassListTable {
    private long id;
    private List<Schoolsubject> schoolsubjectList;
    private Date date;
    private String content;
    private Time timeTo;
    private Time timeFrom;
    private ClassTable classTable;

    public ClassListTable() {
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


    @OneToMany(mappedBy = "classListTable")
    public List<Schoolsubject> getSchoolsubjectList() {
        return schoolsubjectList;
    }

    public void setSchoolsubjectList(List<Schoolsubject> schoolsubjectList) {
        this.schoolsubjectList = schoolsubjectList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Time getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Time timeTo) {
        this.timeTo = timeTo;
    }

    public Time getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Time timeFrom) {
        this.timeFrom = timeFrom;
    }

    @ManyToOne
    public ClassTable getClassTable() {
        return classTable;
    }

    public void setClassTable(ClassTable classTable) {
        this.classTable = classTable;
    }
}
