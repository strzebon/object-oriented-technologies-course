package pl.agh.edu.pl.school.course;

import org.springframework.stereotype.Service;
import pl.agh.edu.pl.school.grade.Grade;
import pl.agh.edu.pl.school.grade.GradeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final GradeRepository gradeRepository;

    public CourseService(CourseRepository courseRepository, GradeRepository gradeRepository) {
        this.courseRepository = courseRepository;
        this.gradeRepository = gradeRepository;
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourse(int id){
        return courseRepository.findById(id);
    }

    public Optional<Double> getGradesAvg(int courseID) {
        return courseRepository.getGradesAvg(courseID);
    }

    public List<Grade> getGrades(int courseId) {
        return gradeRepository.findAll()
                .stream()
                .filter(grade -> grade.getCourse().getId() == courseId)
                .toList();
    }
}
