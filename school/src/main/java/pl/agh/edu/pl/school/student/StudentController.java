package pl.agh.edu.pl.school.student;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.agh.edu.pl.school.course.Course;
import pl.agh.edu.pl.school.grade.Grade;
import pl.agh.edu.pl.school.grade.GradeDto;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDto> getStudents(@RequestParam(value = "indexNumber", required = false) String indexNumber) {
        if (indexNumber != null){
            return studentService.getStudent(indexNumber)
                    .map(StudentDto::from)
                    .map(List::of)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//                    .stream()
//                    .map(StudentDto::from)
//                    .toList();

        }

        return studentService.getStudents()
                .stream()
                .map(StudentDto::from)
                .toList();
    }

    @PostMapping("/{id}/grades")
    public GradeDto giveGrade(@PathVariable("id") int studentId, @RequestBody GradeDto gradeRequest) {
        return studentService.giveGrade(studentId, gradeRequest.value(), gradeRequest.courseName())
                .map(GradeDto::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("{id}/grades")
    public List<GradeDto> getGrades(@PathVariable("id") int studentId) {
        return studentService.getStudent(studentId)
                .map(student -> student.getGrades()
                        .stream()
                        .map(GradeDto::from)
                        .toList()
                ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("{id}/grades/avg")
    public double getGradesAvg(@PathVariable("id") int studentId) {
        return studentService.getGradesAvg(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
