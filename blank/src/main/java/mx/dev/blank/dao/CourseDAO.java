package mx.dev.blank.dao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import mx.dev.blank.entity.Course;
import mx.dev.blank.entity.CourseTeacher;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CourseDAO {

  void create(@NotNull Course course);

  void update(@NotNull Course course);

  void delete(@NotNull Course course);

  void deleteByKeycodePrefix(@NotBlank String prefix);

  Course getByKeycode(@NotBlank String keycode);

  CourseTeacher getCourseAssignation(@NotBlank String courseKeycode, @NotBlank String teacherUuid);
}
