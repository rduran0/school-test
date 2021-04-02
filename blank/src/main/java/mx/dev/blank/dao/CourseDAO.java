package mx.dev.blank.dao;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import mx.dev.blank.entity.Course;
import mx.dev.blank.web.controller.request.CourseFilterRequest;

import org.springframework.validation.annotation.Validated;

@Validated
public interface CourseDAO {

  void create(@NotNull Course course);

  void update(@NotNull Course course);

  void delete(@NotNull Course course);

  void deleteByKeycodePrefix(@NotBlank String prefix);
  
  Course findById(@Min(1) long courseId);
  
  List<Course> getCourses(CourseFilterRequest request);
  
  int countTeachersById(@Min(1) long courseId);
}
