package mx.dev.blank.dao;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

import mx.dev.blank.entity.Course;
import mx.dev.blank.entity.CourseTeacher;
import mx.dev.blank.entity.Student;
import org.springframework.validation.annotation.Validated;

@Validated
public interface StudentDAO {

  List<Student> getStudentsBySearchCriteria(
      String nameQuery,
      String uuidQuery,
      @NotNull Date rangeStart,
      @NotNull Date rangeEnd);

  List<Course> getCourseByStudent(String uuidQuery);

  List<Course> getCourseTeacherByStudent(String uuidQuery);

  List<CourseTeacher> getCourseByDate(final Date startDate,final Date endDate,String day);

  List<CourseTeacher> getCourseByDate(Date startDate, Date endDate);

  List<Course> getCoursesWithoutGrade(String uuidQuery);
}
