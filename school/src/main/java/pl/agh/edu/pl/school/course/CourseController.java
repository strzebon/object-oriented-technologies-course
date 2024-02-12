package pl.agh.edu.pl.school.course;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.agh.edu.pl.school.grade.GradeDto;
import pl.agh.edu.pl.school.student.StudentDto;

import java.util.List;

@RestController
@RequestMapping(path = "courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDto> getCourses() {
        return courseService.getCourses()
                .stream()
                .map(course -> new CourseDto(course.getId(), course.getName()))
                .toList();
    }

    @GetMapping("/{id}/students")
    public List<StudentDto> getStudents(@PathVariable("id") int courseId) {
        return courseService.getCourse(courseId)
                .map(Course::getStudents)
                .map(students -> students
                        .stream()
                        .map(StudentDto::from)
                        .toList())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/grades")
    public List<GradeDto> getGrades(@PathVariable("id") int courseId) {
        return courseService.getGrades(courseId)
                .stream()
                .map(GradeDto::from)
                .toList();
    }

    @GetMapping("/{id}/grades/avg")
    public Double getGradesAvg(@PathVariable("id") int courseId) {
        return courseService.getGradesAvg(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
