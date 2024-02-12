package pl.agh.edu.pl.school.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByName(String name);

    @Query(value = "select avg(g.gradeValue) from Grade g where g.course.id = :courseId")
    Optional<Double> getGradesAvg(@Param("courseId") int courseId);
}
