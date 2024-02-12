package pl.agh.edu.pl.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import pl.agh.edu.pl.school.course.Course;
import pl.agh.edu.pl.school.course.CourseRepository;
import pl.agh.edu.pl.school.grade.Grade;
import pl.agh.edu.pl.school.grade.GradeRepository;
import pl.agh.edu.pl.school.student.Student;
import pl.agh.edu.pl.school.student.StudentRepository;
import pl.agh.edu.pl.school.student.StudentService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Test
    void getStudentsTest() {
        Student student1 = new Student("Adam", "Pajor", LocalDate.now(), "324489");
        Student student2 = new Student("Kaja", "Wajda", LocalDate.now(), "123442");
        List<Student> students = List.of(student1, student2);
        studentRepository.saveAll(students);

        assertEquals(students, studentService.getStudents());
    }

    @Test
    void getStudentByIndexTest() {
        Student student1 = new Student("Adam", "Pajor", LocalDate.now(), "324489");
        Student student2 = new Student("Kaja", "Wajda", LocalDate.now(), "123442");
        List<Student> students = List.of(student1, student2);
        studentRepository.saveAll(students);

        assertEquals(student1, studentService.getStudent("324489").get());
        assertTrue(studentService.getStudent("453433").isEmpty());
    }

    @Test
    void giveGradeTest() {
        Student student1 = new Student("Adam", "Pajor", LocalDate.now(), "324489");
        studentRepository.save(student1);
        Course course = new Course("ASD");
        course.assignStudent(student1);
        courseRepository.save(course);
        studentService.giveGrade(student1.getId(), 4.0, "ASD");
        Grade grade = gradeRepository.findAll().get(0);
        assertEquals(4.0, grade.getGradeValue());
        assertEquals("ASD", grade.getCourse().getName());
        assertEquals(4.0, student1.getGrades().get(0).getGradeValue());
        assertEquals("ASD", student1.getGrades().get(0).getCourse().getName());
    }

    @Test
    void getGradesAvgTest() {
        Student student1 = new Student("Adam", "Pajor", LocalDate.now(), "324489");
        studentRepository.save(student1);
        Course course1 = new Course("ASD");
        Course course2 = new Course("WDI");
        Course course3 = new Course("WDAI");
        course1.assignStudent(student1);
        course2.assignStudent(student1);
        course3.assignStudent(student1);
        courseRepository.saveAll(List.of(course1, course2, course3));
        studentService.giveGrade(student1.getId(), 5.0, "ASD");
        studentService.giveGrade(student1.getId(), 5.0, "WDI");
        studentService.giveGrade(student1.getId(), 2.0, "WDAI");

        assertEquals(4.0, studentService.getGradesAvg(student1.getId()).get());
    }
}
