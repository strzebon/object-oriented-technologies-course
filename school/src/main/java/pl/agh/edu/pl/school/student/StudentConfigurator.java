package pl.agh.edu.pl.school.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.agh.edu.pl.school.course.Course;
import pl.agh.edu.pl.school.course.CourseRepository;
import pl.agh.edu.pl.school.grade.Grade;
import pl.agh.edu.pl.school.grade.GradeRepository;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfigurator {
//    @Bean
//    CommandLineRunner commandLineRunner(StudentRepository studentRepository, CourseRepository courseRepository, GradeRepository gradeRepository) {
//        return args -> {
//            if (studentRepository.count() == 0) {
//                Student student1 = new Student("Jan", "Kowalski", LocalDate.now(), "123456");
//                Student student2 = new Student("Adam", "Nowak", LocalDate.now(), "789322");
//                Student student3 = new Student("Maja", "Wach", LocalDate.now(), "234891");
//                studentRepository.saveAll(List.of(student1, student2, student3));
//
//                Course course1 = new Course("WDI");
//                Course course2 = new Course("ASD");
//                Course course3 = new Course("TOiZO");
//
//                course1.assignStudent(student1);
//                course1.assignStudent(student2);
//                course1.assignStudent(student3);
//                course2.assignStudent(student1);
//                course2.assignStudent(student2);
//                course3.assignStudent(student1);
//                courseRepository.saveAll(List.of(course1, course2, course3));
//
//                Grade grade1 = new Grade(5, course1);
//                Grade grade2 = new Grade(2, course1);
//                Grade grade3 = new Grade(4.5, course1);
//                Grade grade4 = new Grade(3.5, course2);
//                Grade grade5 = new Grade(5, course2);
//                gradeRepository.saveAll(List.of(grade1, grade2, grade3, grade4, grade5));
//
//                student1.giveGrade(grade1);
//                student2.giveGrade(grade2);
//                student3.giveGrade(grade3);
//                student1.giveGrade(grade4);
//                student2.giveGrade(grade5);
//                studentRepository.saveAll(List.of(student1, student2, student3));
//
////                studentRepository.saveAll(List.of(student1, student2, student3));
////                courseRepository.saveAll(List.of(course1, course2, course3));
////                gradeRepository.saveAll(List.of(grade1, grade2, grade3, grade4, grade5));
//            }
//        };
//    }
}
