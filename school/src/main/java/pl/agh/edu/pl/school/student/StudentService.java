package pl.agh.edu.pl.school.student;

import org.springframework.stereotype.Service;
import pl.agh.edu.pl.school.course.Course;
import pl.agh.edu.pl.school.course.CourseRepository;
import pl.agh.edu.pl.school.grade.Grade;
import pl.agh.edu.pl.school.grade.GradeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final GradeRepository gradeRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.gradeRepository = gradeRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudent(String indexNumber) {
        return studentRepository.findByIndexNumber(indexNumber);
    }

    public Optional<Student> getStudent(int id) {
        return studentRepository.findById(id);
    }
    @Transactional
    public Optional<Grade> giveGrade(int studentId, double gradeValue, String courseName) {
        Optional<Student> foundStudent = studentRepository.findById(studentId);
        if (foundStudent.isEmpty()) return Optional.empty();
        Student student = foundStudent.get();
        Optional<Course> foundCourse = courseRepository.findByName(courseName);
        if (foundCourse.isEmpty()) return Optional.empty();
        Course course = foundCourse.get();
        Grade grade = new Grade(gradeValue, course);
        gradeRepository.save(grade);
        student.giveGrade(grade);
        studentRepository.save(student);
        return Optional.of(grade);
    }

    public Optional<Double> getGradesAvg(int studentId) {
        return studentRepository.getGradesAvg(studentId);
//        return getStudent(studentId)
//                .map(student -> student.getGrades()
//                        .stream()
//                        .mapToDouble(Grade::getGradeValue)
//                        .average()
//                        .orElse(0)
//                );
    }
}
