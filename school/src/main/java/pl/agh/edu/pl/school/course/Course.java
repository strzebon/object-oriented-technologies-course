package pl.agh.edu.pl.school.course;

import pl.agh.edu.pl.school.student.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToMany
    private List<Student> students;

    public Course(String name) {
        this.name = name;
        students = new ArrayList<>();
    }

    public Course() {}

    public void assignStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Student> getStudents() {
        return students;
    }
}
