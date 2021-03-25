package mx.dev.blank.dao;

import java.util.List;
import mx.dev.blank.model.CourseRoomDTO;
import org.springframework.validation.annotation.Validated;

@Validated
public interface TeacherDAO {

  List<CourseRoomDTO> getAssignedCourseRoomsByTeacherId(final int id);
}
