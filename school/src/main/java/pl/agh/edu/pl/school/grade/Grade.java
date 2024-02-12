package pl.agh.edu.pl.school.grade;

import pl.agh.edu.pl.school.course.Course;

import javax.persistence.*;

@Entity
public class Grade {
    @Id
    @GeneratedValue
    private int id;
    private double gradeValue;
    @ManyToOne
    private Course course;

    public Grade() {}

    public Grade(double gradeValue, Course course) {
        this.gradeValue = gradeValue;
        this.course = course;
    }
    public double getGradeValue() {
        return gradeValue;
    }

    public Course getCourse() {
        return course;
    }

    public int getId() {
        return id;
    }
}
