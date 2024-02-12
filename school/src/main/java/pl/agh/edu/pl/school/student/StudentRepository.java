package pl.agh.edu.pl.school.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByIndexNumber(String indexNumber);

    @Query(value = "select avg(g.gradeValue) from Student s join s.grades g where s.id = :studentId")
    Optional<Double> getGradesAvg(@Param("studentId") int studentId);
}
