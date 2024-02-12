package pl.agh.edu.pl.school.course;

public record CourseDto(int id, String name) {

    public static CourseDto from(Course course) {
        return new CourseDto(course.getId(), course.getName());
    }
}
