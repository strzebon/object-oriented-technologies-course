package pl.agh.edu.pl.school.grade;

public record GradeDto(double value, String courseName) {

    public static GradeDto from(Grade grade) {
        return new GradeDto(grade.getGradeValue(), grade.getCourse().getName());
    }
}
